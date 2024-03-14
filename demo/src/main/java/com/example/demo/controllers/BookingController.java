package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Appointment;
import com.example.demo.models.Booking;
import com.example.demo.repositories.BookingRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;


    @GetMapping("book-appointment")
    public ModelAndView bookAppointment() {
        ModelAndView mav = new ModelAndView("/user/booking.html");
        Booking booking = new Booking();
        mav.addObject("bookings", booking);
        return mav;
    }

    @SuppressWarnings("null")
    @PostMapping("booked")
    public String save(@ModelAttribute Booking booking) {
        this.bookingRepository.save(booking);
        //return new RedirectView("booked");
        return "booked";
    }
    

}
