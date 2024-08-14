package com.project.banking.mapper;

import com.project.banking.dto.AccountDto;
import com.project.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(), account.getAccountHolderName(), account.getBalance()
        );
    }
}
