package com.example.demo.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String email;
    private String phone;
    private String password;
}
