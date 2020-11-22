package com.gfg.majorprojectjdbl8.transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    public Optional<Transaction> findByExternalId(String externalID);
}
