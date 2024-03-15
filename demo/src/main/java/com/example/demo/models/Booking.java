package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;

    // @ManyToOne
    // private Doctor doctor;

    // @OneToOne
    // private User user;



    public Booking() {
    }

    public Booking(int id, LocalDate date, LocalTime time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Booking id(int id) {
        setId(id);
        return this;
    }

    public Booking date(LocalDate date) {
        setDate(date);
        return this;
    }

    public Booking time(LocalTime time) {
        setTime(time);
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
        return id == booking.id && Objects.equals(date, booking.date) && Objects.equals(time, booking.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
   
}
