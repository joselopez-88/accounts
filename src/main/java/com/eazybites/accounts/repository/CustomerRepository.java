package com.eazybites.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybites.accounts.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByMobileNumber(String mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
}
