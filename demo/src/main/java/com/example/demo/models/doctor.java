package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class doctor {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected int number;
    protected String name;
    protected String email;
    protected String password;
    protected String gender;

    public doctor() {
    }

    public doctor(int id, int number, String name, String email, String password, String gender) {
        this.id = id;
        this.number = number;
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

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public doctor id(int id) {
        setId(id);
        return this;
    }

    public doctor number(int number) {
        setNumber(number);
        return this;
    }

    public doctor name(String name) {
        setName(name);
        return this;
    }

    public doctor email(String email) {
        setEmail(email);
        return this;
    }

    public doctor password(String password) {
        setPassword(password);
        return this;
    }

    public doctor gender(String gender) {
        setGender(gender);
        return this;
    }

    // @Override
    // public boolean equals(Object o) {
    //   return EqualsBuilder.reflectionEquals(this, o);
    // }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, email, password, gender);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", number='" + getNumber() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", gender='" + getGender() + "'" +
            "}";
    }
    
}
