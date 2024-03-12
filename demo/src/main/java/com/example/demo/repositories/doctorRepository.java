package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.doctor;

@Repository

public interface doctorRepository extends JpaRepository<doctor, Integer> {

    Optional<doctor> findById(Long id);

    void deleteById(Long id);
}
