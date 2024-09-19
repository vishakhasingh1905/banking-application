# ABCD Banking Application

## Overview

This banking application is a simple yet functional implementation built using Spring Boot. It provides core banking functionalities such as account creation, deposit, withdrawal, fund transfer, and account management. Additionally, the application maintains a transaction history for transparency and auditing purposes.

## Features

- **Account Management:** Create new accounts and manage existing ones.
- **Transactions:**
    - **Deposit Funds:** Add money to an account.
    - **Withdraw Funds:** Remove money from an account.
    - **Transfer Funds:** Move money between accounts.
- **Transaction History:** Record and retrieve all financial transactions.
- **Account Status Management:** Lock or unlock accounts based on operational needs.

## Business Specifications

1. **Account Management**
    - Create a new account.
    - Retrieve account details.

2. **Transactions**
    - Deposit funds into an account.
    - Withdraw funds from an account (with balance validation).
    - Transfer funds between two accounts.

3. **Transaction History**
    - View transaction history for any account.

4. **Account Status Management**
    - Lock an account to prevent transactions.
    - Unlock a previously locked account.

## Technical Specifications

### System Architecture

The application uses a standard Spring Boot architecture with RESTful APIs for client-server communication. It leverages Spring Data JPA for database interactions.

### Key Components

- **Entities:**
    - **Account:** Represents a bank account.
    - **Transaction:** Represents a financial transaction.

- **DTOs (Data Transfer Objects):**
    - **AccountDto:** Transfers account data.
    - **TransactionDto:** Transfers transaction data.

- **Repositories:**
    - **AccountRepository:** Manages CRUD operations for accounts.
    - **TransactionRepository:** Manages CRUD operations for transactions and custom queries for transaction history.

- **Service Layer:**
    - **AccountService:** Contains business logic for account operations.
    - **AccountServiceImpl:** Implements the business logic.

- **Mappers:**
    - **AccountMapper:** Maps between `Account` and `AccountDto`.
    - **TransactionMapper:** Maps between `Transaction` and `TransactionDto`.

- **Controller:**
    - **AccountController:** Provides REST endpoints for account and transaction management.

### Security Considerations

- **Account Locking:** Prevents unauthorized transactions by locking accounts.
- **Transaction Validation:** Ensures transactions are valid based on account balance and status.

### Error Handling

Comprehensive error handling for scenarios such as insufficient funds, account not found, and invalid transaction requests.

### Database Design

- **Accounts Table:**
    - `id`: Primary key.
    - `account_holder_name`: Name of the account holder.
    - `balance`: Current balance.
    - `status`: Account status (ACTIVE or LOCKED).

- **Transactions Table:**
    - `id`: Primary key.
    - `account_id`: Foreign key linking to the account.
    - `transaction_type`: Type of transaction (DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT).
    - `amount`: Transaction amount.
    - `timestamp`: Timestamp of the transaction.
    - `description`: Description of the transaction.

### API Endpoints

- **Account Management:**
    - `POST /api/accounts/addAccount`: Create a new account.
    - `GET /api/accounts/getAccount/{id}`: Retrieve account details by ID.

- **Transaction Operations:**
    - `PUT /api/accounts/deposit`: Deposit funds into an account.
    - `PUT /api/accounts/withdraw`: Withdraw funds from an account.
    - `PUT /api/accounts/transfer`: Transfer funds between accounts.
    - `GET /api/accounts/{accountId}/transactions`: Retrieve transaction history for an account.

- **Account Status Management:**
    - `PUT /api/accounts/lock/{accountId}`: Lock an account.
    - `PUT /api/accounts/unlock/{accountId}`: Unlock an account.

### Enhancements and Future Work

- **Overdraft Protection:** Allow or deny transactions based on configurable limits.
- **Multi-Currency Support:** Support for multiple currencies.
- **User Authentication and Roles:** Implement authentication and role-based access control.
- **Reporting and Analytics:** Provide insights into account activity with reports and summaries.

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/banking-application.git
