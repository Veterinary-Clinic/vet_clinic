package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Phonenumber;
    private String name;
    private String email;
    private String password;
    private String gender;


    public Doctor() {
    }

    public Doctor(int id, String Phonenumber, String name, String email, String password, String gender) {
        this.id = id;
        this.Phonenumber = Phonenumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return this.Phonenumber;
    }

    public void setPhonenumber(String Phonenumber) {
        this.Phonenumber = Phonenumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Doctor id(int id) {
        setId(id);
        return this;
    }

    public Doctor Phonenumber(String Phonenumber) {
        setPhonenumber(Phonenumber);
        return this;
    }

    public Doctor name(String name) {
        setName(name);
        return this;
    }

    public Doctor email(String email) {
        setEmail(email);
        return this;
    }

    public Doctor password(String password) {
        setPassword(password);
        return this;
    }

    public Doctor gender(String gender) {
        setGender(gender);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return id == doctor.id && Objects.equals(Phonenumber, doctor.Phonenumber) && Objects.equals(name, doctor.name) && Objects.equals(email, doctor.email) && Objects.equals(password, doctor.password) && Objects.equals(gender, doctor.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Phonenumber, name, email, password, gender);
    }

    @Override
    public String toString() {
        return name + gender;
    }

    
}