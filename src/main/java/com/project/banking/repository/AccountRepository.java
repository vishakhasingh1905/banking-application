package com.project.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.banking.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
}
