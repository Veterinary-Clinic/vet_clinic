package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Appointment;
import com.example.demo.models.Booking;
import com.example.demo.models.Doctor;
import com.example.demo.models.User;
import com.example.demo.repositories.AppointmentRepository;
import com.example.demo.repositories.BookingRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/booking")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("booked")
    public void book(@RequestParam int id, HttpServletResponse response, HttpSession session) throws IOException {
        Appointment appointment = appointmentRepository.findById(id).get();
        Booking booking = new Booking();
        Long userId = (Long) session.getAttribute("user_id");

        if (userId != null) {
            User user = new User();
            user.setId(userId);
            booking.setUser(user);
            booking.setDate(appointment.getDate());
            booking.setTime(appointment.getStartHr());
            booking.setDoctor(appointment.getDoctor());
            this.bookingRepository.save(booking);
            this.appointmentRepository.delete(appointment);
            response.sendRedirect("/appointments/available-appointments");
        }
        else{
            response.sendRedirect("/user/Login");
        }
    }

}
