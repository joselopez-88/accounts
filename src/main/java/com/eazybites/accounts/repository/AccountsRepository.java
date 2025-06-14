package com.eazybites.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybites.accounts.model.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{
    Optional<Accounts> findByCustomerId(Long customerId);

}
