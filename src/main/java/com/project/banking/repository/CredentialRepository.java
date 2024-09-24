package com.project.banking.repository;

import com.project.banking.entity.Credentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<Credentials, String> {
}

