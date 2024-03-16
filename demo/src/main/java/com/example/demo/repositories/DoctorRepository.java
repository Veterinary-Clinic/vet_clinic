package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Doctor;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findById(Long id);

    void deleteById(Long id);

    Doctor findByname(String name);
}