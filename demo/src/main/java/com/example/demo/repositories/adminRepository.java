package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.admin;

@Repository
public interface adminRepository extends JpaRepository<admin,Integer>{

    Optional<admin> findById(Long id);

    
}

