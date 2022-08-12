package com.works.repositories;

import com.works.entitites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmailEqualsAndPasswordEquals(String email, String password);

}