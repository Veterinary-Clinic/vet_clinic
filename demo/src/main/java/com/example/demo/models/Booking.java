package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String date;
    private String time;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private User user;


    public Booking() {
    }

    public Booking(int id, String date, String time, Doctor doctor, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking id(int id) {
        setId(id);
        return this;
    }

    public Booking date(String date) {
        setDate(date);
        return this;
    }

    public Booking time(String time) {
        setTime(time);
        return this;
    }

    public Booking doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    public Booking user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Booking)) {
            return false;
        }
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(date, booking.date) && Objects.equals(time, booking.time) && Objects.equals(doctor, booking.doctor) && Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, doctor, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", doctor='" + getDoctor() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

   
}
