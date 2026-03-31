package com.example.api.service;

import com.example.api.model.Account;
import com.example.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Account Service - Converted from .NET to Java
 * Business logic for account management
 */
@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Get all accounts
     * @return List of all accounts
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Get account by ID
     * @param id Account ID
     * @return Account or null if not found
     */
    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    /**
     * Get account by account number
     * @param accountNumber Account number
     * @return Account or null if not found
     */
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    /**
     * Create new account
     * @param account Account details
     * @return Created account
     */
    public Account createAccount(Account account) {
        // Validate account
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            throw new IllegalArgumentException("Account number is required");
        }
        
        // Check if account number already exists
        Account existingAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        if (existingAccount != null) {
            throw new IllegalArgumentException("Account number already exists");
        }

        return accountRepository.save(account);
    }

    /**
     * Update existing account
     * @param id Account ID
     * @param accountDetails Updated account details
     * @return Updated account or null if not found
     */
    public Account updateAccount(Long id, Account accountDetails) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            
            // Update fields
            if (accountDetails.getAccountHolderName() != null) {
                account.setAccountHolderName(accountDetails.getAccountHolderName());
            }
            if (accountDetails.getAccountType() != null) {
                account.setAccountType(accountDetails.getAccountType());
            }
            if (accountDetails.getBalance() != null) {
                account.setBalance(accountDetails.getBalance());
            }
            if (accountDetails.getCurrency() != null) {
                account.setCurrency(accountDetails.getCurrency());
            }
            if (accountDetails.getStatus() != null) {
                account.setStatus(accountDetails.getStatus());
            }
            
            return accountRepository.save(account);
        }
        
        return null;
    }

    /**
     * Delete account
     * @param id Account ID
     * @return true if deleted, false if not found
     */
    public boolean deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Get account balance
     * @param id Account ID
     * @return Balance or null if account not found
     */
    public Double getAccountBalance(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(Account::getBalance).orElse(null);
    }

    /**
     * Deposit money to account
     * @param id Account ID
     * @param amount Amount to deposit
     * @return Updated account or null if not found
     */
    public Account deposit(Long id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        
        return null;
    }

    /**
     * Withdraw money from account
     * @param id Account ID
     * @param amount Amount to withdraw
     * @return Updated account or null if not found or insufficient funds
     */
    public Account withdraw(Long id, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            
            if (account.getBalance() < amount) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            
            account.setBalance(account.getBalance() - amount);
            return accountRepository.save(account);
        }
        
        return null;
    }

    /**
     * Get accounts by status
     * @param status Account status
     * @return List of accounts with the specified status
     */
    public List<Account> getAccountsByStatus(String status) {
        return accountRepository.findByStatus(status);
    }
}
