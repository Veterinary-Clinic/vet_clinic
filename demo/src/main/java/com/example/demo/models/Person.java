package com.example.demo.models;
import java.util.Objects;

public abstract class Person {
    protected int id;
    protected int number;
    protected String name;
    protected String email;
    protected String password;
    protected String gender;



    //constructors
    public Person() {
    }

    public Person(int id, int number, String name, String email, String password, String gender) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    //Getters & Setters
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

    public Person id(int id) {
        setId(id);
        return this;
    }

    public Person number(int number) {
        setNumber(number);
        return this;
    }

    public Person name(String name) {
        setName(name);
        return this;
    }

    public Person email(String email) {
        setEmail(email);
        return this;
    }

    public Person password(String password) {
        setPassword(password);
        return this;
    }

    public Person gender(String gender) {
        setGender(gender);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && number == person.number && Objects.equals(name, person.name) && Objects.equals(email, person.email) && Objects.equals(password, person.password) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, email, password, gender);
    }

    public abstract void authorize();

    public boolean isAuthenticated() {
        boolean isAuthenticatedFlag = false;
        return isAuthenticatedFlag;
    }

    public void login(){
        if(isAuthenticated()){

        }
    }
    public void logout(){


    }
    public void editProfileInfo(){

    }
    
}
