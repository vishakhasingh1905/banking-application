package com.project.banking.service;
import com.project.banking.dto.SignInRequest;
import com.project.banking.dto.SignUpRequest;
import com.project.banking.entity.Account;
import com.project.banking.entity.Credentials;
import com.project.banking.repository.AccountRepository;
import com.project.banking.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignInService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void signIn(SignInRequest signInRequest) {
        // Find credentials by username
        Optional<Credentials> credentialsOpt = credentialRepository.findByUsername(signInRequest.getUsername());
        if (credentialsOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password.");
        }

        Credentials credentials = credentialsOpt.get();

        // Validate password
        if (!credentials.getPassword().equals(signInRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }

        // Check if the account exists
        Optional<Account> accountOpt = accountRepository.findById(Long.parseLong(signInRequest.getUsername()));
        if (accountOpt.isEmpty()) {
            throw new RuntimeException("The account associated with this username no longer exists.");
        }

        System.out.println("User signed in successfully.");
    }
}



