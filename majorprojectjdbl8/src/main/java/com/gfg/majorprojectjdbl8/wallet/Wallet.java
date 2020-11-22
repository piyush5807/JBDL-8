package com.gfg.majorprojectjdbl8.wallet;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internalId")
    Long id;
    String userId;
    Double balance;
}
