package com.example.demo.controllers;

import com.example.demo.models.Doctor;
import com.example.demo.models.User;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.UserRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
     private  DoctorRepository doctorRepository;

    @GetMapping("")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("/user/index.html");
        return mav;
    } 


    @GetMapping("Registration") 
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("/user/Signup.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
    
    @PostMapping("Registration")
    public String saveUser(@ModelAttribute User user) {
        String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
        return "Added";
    }

    @GetMapping("doctors")
    public ModelAndView getDoctors() {
        ModelAndView mav = new ModelAndView("/user/doctors.html");
        List<Doctor> doctors = doctorRepository.findAll();
        mav.addObject("doctors", doctors);
        return mav;
    }

}