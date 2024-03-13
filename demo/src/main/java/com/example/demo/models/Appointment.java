package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    LocalDate date;
    LocalTime startHr;
    LocalTime endHr;
    

    public Appointment() {
    }

    public Appointment(int id, LocalDate date, LocalTime startHr, LocalTime endHr) {
        this.id = id;
        this.date = date;
        this.startHr = startHr;
        this.endHr = endHr;
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

    public LocalTime getStartHr() {
        return this.startHr;
    }

    public void setStartHr(LocalTime startHr) {
        this.startHr = startHr;
    }

    public LocalTime getEndHr() {
        return this.endHr;
    }

    public void setEndHr(LocalTime endHr) {
        this.endHr = endHr;
    }

    public Appointment id(int id) {
        setId(id);
        return this;
    }

    public Appointment date(LocalDate date) {
        setDate(date);
        return this;
    }

    public Appointment startHr(LocalTime startHr) {
        setStartHr(startHr);
        return this;
    }

    public Appointment endHr(LocalTime endHr) {
        setEndHr(endHr);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Appointment)) {
            return false;
        }
        Appointment appointment = (Appointment) o;
        return id == appointment.id && Objects.equals(date, appointment.date) && Objects.equals(startHr, appointment.startHr) && Objects.equals(endHr, appointment.endHr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startHr, endHr);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", startHr='" + getStartHr() + "'" +
            ", endHr='" + getEndHr() + "'" +
            "}";
    }

    
}
