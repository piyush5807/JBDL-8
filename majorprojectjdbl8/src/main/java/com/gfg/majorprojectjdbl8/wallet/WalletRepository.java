package com.gfg.majorprojectjdbl8.wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findWalletByUserId(String externalID);
}
