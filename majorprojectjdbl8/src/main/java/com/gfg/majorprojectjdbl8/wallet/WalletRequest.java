package com.gfg.majorprojectjdbl8.wallet;
import com.gfg.majorprojectjdbl8.transaction.TransactionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletRequest {
    String transactionId;
    String fromUser;
    String toUser;
    Double amount;
    TransactionType transactionType;
}
