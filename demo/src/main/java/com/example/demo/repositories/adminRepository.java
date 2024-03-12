package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.admin;

@Repository
public interface adminRepository extends JpaRepository<admin,Integer>{

    
}

