package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String startHr;
    private String endHr;

    @Transient
    private LocalTime unFormattedStartHr;
    @Transient
    private LocalTime unFormattedEndHr;
 
    @ManyToOne
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(int id, LocalDate date, String startHr, String endHr, LocalTime unFormattedStartHr,
            LocalTime unFormattedEndHr, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.startHr = startHr;
        this.endHr = endHr;
        this.unFormattedStartHr = unFormattedStartHr;
        this.unFormattedEndHr = unFormattedEndHr;
        this.doctor = doctor;
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

    public LocalTime getUnFormattedStartHr() {
        return this.unFormattedStartHr;
    }

    public void setUnFormattedStartHr(LocalTime unFormattedStartHr) {
        this.unFormattedStartHr = unFormattedStartHr;
    }

    public LocalTime getUnFormattedEndHr() {
        return this.unFormattedEndHr;
    }

    public void setUnFormattedEndHr(LocalTime unFormattedEndHr) {
        this.unFormattedEndHr = unFormattedEndHr;
    }

    public String getStartHr() {
        return this.startHr;
    }

    public void setStartHr(LocalTime unFormattedStartHr) {
        this.startHr = formatTime(unFormattedStartHr);
    }

    public String getEndHr() {
        return this.endHr;
    }

    public void setEndHr(LocalTime endHr) {
        this.endHr = formatTime(unFormattedEndHr);
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDoctorName(){
        return this.doctor.getName();
    }
    public long getDoctorId(){
        return this.doctor.getId();
    }


    

    private String formatTime(LocalTime unFormattedTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
        return unFormattedTime.format(formatter);
    }

    // private int timeSplit(String time){
    //     String[] timeParts = time.split(":");
    //     int hour = Integer.parseInt(timeParts[0]);
    //     //int minute = Integer.parseInt(timeParts[1]);
    //     return hour;
    // }


    // public String clockTimeConversion(int hour) {
    //     if (hour == 12) {
    //         return hour + ":00 PM";
    //     } else if (hour > 12) {
    //         return (hour - 12) + ":00 PM";
    //     } else {
    //         return hour + ":00 AM";
    //     }
    // }

    // private void hourList(){
    //     int startHour = timeSplit(this.startHr);
    //     int endHour = timeSplit(this.endHr);
        
    //     for (int hour = startHour; hour <= endHour; hour++) {
    //         System.out.println(clockTimeConversion(hour));
    //         //clockTimeConversion(hour);
    //     }
    // }

    // private int numberOfWorkingHours(){
    //     int startHour = timeSplit(this.startHr);
    //     int endHour = timeSplit(this.endHr);
    //     return endHour - startHour; 
    // }


}