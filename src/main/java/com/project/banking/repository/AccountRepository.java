package com.project.banking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.banking.entity.Account;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findById(long id);

}
