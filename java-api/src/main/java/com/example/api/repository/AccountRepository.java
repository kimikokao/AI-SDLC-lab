package com.example.api.repository;

import com.example.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository - Converted from .NET to Java
 * Data access layer for Account entity
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find account by account number
     * @param accountNumber Account number
     * @return Account or null if not found
     */
    Account findByAccountNumber(String accountNumber);

    /**
     * Find accounts by status
     * @param status Account status
     * @return List of accounts with the specified status
     */
    List<Account> findByStatus(String status);

    /**
     * Find accounts by account holder name
     * @param accountHolderName Account holder name
     * @return List of accounts
     */
    List<Account> findByAccountHolderNameContainingIgnoreCase(String accountHolderName);

    /**
     * Find accounts by account type
     * @param accountType Account type
     * @return List of accounts
     */
    List<Account> findByAccountType(String accountType);

    /**
     * Check if account exists by account number
     * @param accountNumber Account number
     * @return true if exists, false otherwise
     */
    boolean existsByAccountNumber(String accountNumber);
}
