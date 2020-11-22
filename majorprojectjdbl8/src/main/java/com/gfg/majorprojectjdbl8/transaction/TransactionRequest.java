package com.gfg.majorprojectjdbl8.transaction;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest {
    private TransactionType transactionType;
    private String fromUser;
    private Double amount;
    private String toUser;
    private String purpose;
}
