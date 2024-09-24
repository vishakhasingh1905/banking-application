package com.project.banking.service;

import com.project.banking.dto.SignUpRequest;
import com.project.banking.entity.Account;
import com.project.banking.entity.Credentials;
import com.project.banking.repository.AccountRepository;
import com.project.banking.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CredentialRepository credentialRepository;
    public void signUp(SignUpRequest signUpRequest) {
        // Check if account exists by account ID

        Optional<Account> accountOpt = accountRepository.findById(Long.parseLong(signUpRequest.getAccountId()));
        if (accountOpt.isEmpty()) {
            throw new RuntimeException("Account does not exist.");
        }

        // Create and save credentials
        Credentials credentials = new Credentials();
        credentials.setUsername(signUpRequest.getAccountId());
        credentials.setPassword(signUpRequest.getPassword());

        credentialRepository.save(credentials);
    }
}
