package com.gfg.majorprojectjdbl8.wallet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorprojectjdbl8.transaction.TransactionStatus;
import com.gfg.majorprojectjdbl8.transaction.TransactionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics = {"walletjdbl82"},groupId = "walletjdbl8")
    public void upsert(String req) throws JsonProcessingException {
        WalletRequest request = objectMapper.readValue(req,WalletRequest.class);
        Wallet fromWallet = walletRepository.findWalletByUserId(request.fromUser)
                .orElse(Wallet.builder().balance(0.0).userId(request.fromUser).build());
        Wallet toWallet = walletRepository.findWalletByUserId(request.toUser)
                .orElse(Wallet.builder().balance(0.0).userId(request.toUser).build());

        Double amount =  request.getAmount();
        fromWallet.setBalance(fromWallet.getBalance()-amount);
        toWallet.setBalance(toWallet.getBalance()+amount);

        if(fromWallet.getBalance()<0.0){
            TransactionUpdateRequest transactionUpdateRequest = TransactionUpdateRequest
                            .builder().transactionId(request.getTransactionId()).status(TransactionStatus.REJECTED.toString())
                            .build();
            //send it back
            kafkaTemplate.send("transactionjdbl8","transaction",objectMapper.writeValueAsString(transactionUpdateRequest));
            return;

        }

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
        TransactionUpdateRequest transactionUpdateRequest = TransactionUpdateRequest
                .builder().transactionId(request.getTransactionId()).status(TransactionStatus.APPROVED.toString())
                .build();
        //send it back
        kafkaTemplate.send("transactionjdbl8","transactionon", objectMapper.writeValueAsString(transactionUpdateRequest));
    }
}
