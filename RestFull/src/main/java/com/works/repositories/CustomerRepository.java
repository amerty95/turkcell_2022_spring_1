package com.works.repositories;

import com.works.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //List<Customer> findByNameContainsIgnoreCase(String name, Pageable pageable);
    Page<Customer> findByNameContainsIgnoreCase(String name, Pageable pageable);

    Optional<Customer> findByEmailEqualsIgnoreCaseAndPasswordEquals(String email, String password);



}