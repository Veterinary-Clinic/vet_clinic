package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Appointment;
import com.example.demo.models.Booking;
import com.example.demo.repositories.AppointmentRepository;
import com.example.demo.repositories.BookingRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user/booking")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("booked")
    public void book(@RequestParam int id, HttpServletResponse response) throws IOException {
        try {
            Appointment appointment = appointmentRepository.findById(id).get();
            Booking booking = new Booking();
            
            booking.setDate(appointment.getDate());
            booking.setTime(appointment.getStartHr());
            booking.setDoctor(appointment.getDoctor());
            //booking.setUser(appointment.getUser());

            this.bookingRepository.save(booking);
            this.appointmentRepository.delete(appointment);

        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
        response.sendRedirect("/appointments/available-appointments");
    }
    

}
