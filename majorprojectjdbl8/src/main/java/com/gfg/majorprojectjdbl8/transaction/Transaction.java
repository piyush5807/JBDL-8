package com.gfg.majorprojectjdbl8.transaction;
import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internalId")
    private Long id;
    private String externalId= UUID.randomUUID().toString();
    private String transactionDateTime;
    private String transactionType;
    private String fromUser;
    private Double amount;
    private String toUser;
    private String purpose;
    private String status;
}
