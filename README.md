# ABCD Banking Application

## Overview

This banking application is built using Spring Boot and provides essential banking functionalities, including account creation, deposits, withdrawals, fund transfers, and transaction history tracking. The backend integrates with MongoDB for persistent storage.

## Features

- **Account Management:** Create and manage accounts.
- **Transactions:**
    - **Deposit Funds:** Add money to an account.
    - **Withdraw Funds:** Withdraw money from an account.
    - **Transfer Funds:** Transfer money between accounts.
- **Transaction History:** Record and retrieve all financial transactions.
- **Account Status Management:** Lock or unlock accounts based on operational needs.
- **User Signup and Login:** Customers can sign up by choosing a password and logging in using their account ID as the username.

## Business Specifications

1. **Account Management**
    - Create a new account.
    - Retrieve account details.
    - Validate accounts exist in MongoDB during signup.

2. **User Signup and Login**
    - Customers choose a password for account creation.
    - Account ID (generated during account creation) is used as the username for login.

3. **Transactions**
    - Deposit funds into an account.
    - Withdraw funds from an account (with balance validation).
    - Transfer funds between two accounts.

4. **Transaction History**
    - View transaction history for any account.

5. **Account Status Management**
    - Lock an account to prevent transactions.
    - Unlock a previously locked account.

## Technical Specifications

### System Architecture

The application uses a Spring Boot architecture with RESTful APIs for client-server communication. Spring Data MongoDB is used for database interactions.

### Key Components

- **Entities:**
    - **Account:** Represents a bank account, stored in MongoDB.
    - **Transaction:** Represents a financial transaction, stored in MongoDB.
    - **UserCredentials:** Stores customer login credentials (account ID as username and password).

- **DTOs (Data Transfer Objects):**
    - **AccountDto:** Transfers account data.
    - **TransactionDto:** Transfers transaction data.
    - **SignupDto:** Handles data for the signup process (account ID and password).

- **Repositories:**
    - **AccountRepository:** Manages CRUD operations for accounts.
    - **TransactionRepository:** Manages CRUD operations for transactions and queries for transaction history.
    - **UserCredentialsRepository:** Manages credentials for login.

- **Service Layer:**
    - **AccountService:** Contains business logic for account operations.
    - **SignupService:** Manages signup and account verification.

- **Mappers:**
    - **AccountMapper:** Maps between `Account` and `AccountDto`.
    - **TransactionMapper:** Maps between `Transaction` and `TransactionDto`.

- **Controller:**
    - **AccountController:** Provides REST endpoints for account and transaction management.
    - **SignupController:** Handles user signup and login validation.

### Security Considerations

- **Password Encryption:** Encrypts passwords before saving to the database.
- **Account Locking:** Prevents unauthorized transactions by locking accounts.
- **Transaction Validation:** Ensures valid transactions based on account balance and status.
- **MongoDB ID Validation:** Ensures the account exists in the database before login.

### Error Handling

- Handles scenarios such as invalid credentials, insufficient funds, account not found, and invalid transaction requests.

### Database Design

- **Accounts Collection:**
    - `_id`: MongoDB auto-generated ID.
    - `id`: Custom account ID.
    - `account_holder_name`: Name of the account holder.
    - `balance`: Current balance.
    - `status`: Account status (ACTIVE or LOCKED).

- **Transactions Collection:**
    - `_id`: MongoDB auto-generated ID.
    - `account_id`: Foreign key linking to the account.
    - `transaction_type`: Type of transaction (DEPOSIT, WITHDRAWAL, TRANSFER).
    - `amount`: Transaction amount.
    - `timestamp`: Transaction date and time.
    - `description`: Optional description of the transaction.

- **UserCredentials Collection:**
    - `account_id`: The unique account ID (used as username).
    - `password`: Encrypted password.

### API Endpoints

- **Account Management:**
    - `POST /api/accounts/addAccount`: Create a new account.
    - `GET /api/accounts/getAccount/{id}`: Retrieve account details by ID.

- **User Signup & Login:**
    - `POST /api/signup`: Customer creates a password and credentials.
    - `POST /api/login`: Login using account ID and password.

- **Transaction Operations:**
    - `PUT /api/accounts/deposit`: Deposit funds into an account.
    - `PUT /api/accounts/withdraw`: Withdraw funds from an account.
    - `PUT /api/accounts/transfer`: Transfer funds between accounts.
    - `GET /api/accounts/{accountId}/transactions`: Retrieve transaction history for an account.

- **Account Status Management:**
    - `PUT /api/accounts/lock/{accountId}`: Lock an account.
    - `PUT /api/accounts/unlock/{accountId}`: Unlock an account.

### Postman Sample Requests

- **Signup:**
    - **URL:** `POST /api/signup`
    - **Body:**
    ```json
    {
      "accountId": "123456",
      "password": "mypassword123"
    }
    ```

- **Login:**
    - **URL:** `POST /api/login`
    - **Body:**
    ```json
    {
      "accountId": "123456",
      "password": "mypassword123"
    }
    ```

### Enhancements and Future Work

- **Overdraft Protection:** Configure overdraft protection for accounts.
- **Multi-Currency Support:** Handle transactions in multiple currencies.
- **User Roles and Authentication:** Add role-based access control for different types of users.
- **Analytics and Reporting:** Provide insights and generate reports for account activity.

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/banking-application.git
