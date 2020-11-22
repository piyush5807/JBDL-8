package com.gfg.majorprojectjdbl8.transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorprojectjdbl8.notification.EmailRequest;
import com.gfg.majorprojectjdbl8.user.User;
import com.gfg.majorprojectjdbl8.wallet.WalletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();

    public void create(TransactionRequest transactionRequest) throws JsonProcessingException {
        //get user details
        URI uri = URI.create("http://localhost:8080/user/"+transactionRequest.getFromUser());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        User fromUser = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();
        User toUser = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();

        Transaction transaction = Transaction.builder()
                .fromUser(transactionRequest.getFromUser())
                .toUser(transactionRequest.getToUser())
                .amount(transactionRequest.getAmount())
                .transactionType(TransactionType.CREDIT.toString())
                .purpose(transactionRequest.getPurpose())
                .status(TransactionStatus.PENDING.toString())
                .externalId(UUID.randomUUID().toString())
                .transactionDateTime(new Date().toString())
                .build();
        transactionRepository.save(transaction);

        //publish event to wallet
        WalletRequest walletRequest = WalletRequest.builder()
                .fromUser(transactionRequest.getFromUser())
                .toUser(transactionRequest.getToUser())
                .amount(transactionRequest.getAmount())
                .transactionType(TransactionType.CREDIT)
                .transactionId(transaction.getExternalId())
                .build();
        kafkaTemplate.send("walletjdbl82","wallet", objectMapper.writeValueAsString(walletRequest));
        log.info("sent to topic {}", "walletjdbl8");
    }

    @KafkaListener(topics = {"transactionjdbl8"},groupId = "transactionjdbl8")
    public void update(String request) throws JsonProcessingException {
        TransactionUpdateRequest updateRequest = objectMapper.readValue(request,TransactionUpdateRequest.class);
        Transaction transaction = transactionRepository.findByExternalId(updateRequest.getTransactionId())
                .get();
        transaction.setStatus(TransactionStatus.valueOf(updateRequest.getStatus().toUpperCase()).toString());
        transactionRepository.save(transaction);
        //Notify user
        URI uri = URI.create("http://localhost:8080/user/"+transaction.getFromUser());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        User user = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getEmail());
        emailRequest.setMessage(String.format("Hi %s /n You transactionid %s got %s",
                user.getUserId(),transaction.getExternalId(),transaction.getStatus()));
        kafkaTemplate.send("emailjdbl8",objectMapper.writeValueAsString(emailRequest));
    }
}
