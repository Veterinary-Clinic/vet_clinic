package com.example.demo.controllers;

import com.example.demo.models.Appointment;
import com.example.demo.models.Doctor;
import com.example.demo.repositories.AppointmentRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/appointments")

public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentsRepository;

    @GetMapping("available-appointments")
    public ModelAndView viewAppointment() {
        ModelAndView mav = new ModelAndView("/user/booking.html");
        List<Appointment> appointments = this.appointmentsRepository.findAll();
        mav.addObject("appointments", appointments);
        return mav;
    }

    @GetMapping("add-appointment")
    public ModelAndView addAppointment() {
        ModelAndView mav = new ModelAndView("/doctors/addAppointments.html");
        Appointment appointment = new Appointment();
        // mav.addObject("", id); // Add doctor's ID to the ModelAndView
        mav.addObject("appointments", appointment);
        return mav;
    }

    @PostMapping("save-appointment")
    public void saveAppointment(@ModelAttribute Appointment appointment, HttpServletResponse response, HttpSession session) throws IOException {
        Long doctorId = (Long) session.getAttribute("doctor_id");
        if (doctorId != null) {
            Doctor doctor = new Doctor();
            doctor.setId(doctorId);
            appointment.setDoctor(doctor);

            appointment.setStartHr(appointment.getUnFormattedStartHr());
            appointment.setEndHr(appointment.getUnFormattedEndHr());
            this.appointmentsRepository.save(appointment);
            response.sendRedirect("add-appointment");
        }
    }

}
