package com.example.demo.controllers;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.Doctor;
import com.example.demo.models.User;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("/user/index.html");
        List<User> users = this.userRepository.findAll();
        mav.addObject("users", users);
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
    public RedirectView saveUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the registration form
            return new RedirectView("registration");
        }

        // Check if the password meets the minimum length requirement
        String password = user.getPassword();
        if (password == null || password.length() < 8) {
            bindingResult.addError(new FieldError("user", "password", "Password must be at least 8 characters long."));
            return new RedirectView("Registration");
        }

        // Check if the password matches the confirm password
        String confirmPassword = user.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            bindingResult.addError(new FieldError("user", "confirmPassword", "Passwords do not match."));
            return new RedirectView("Registration");
        }

        // Hash the password and save the user
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        user.setPassword(encodedPassword);
        userRepository.save(user);

        // Redirect to the home page after successful registration
        return new RedirectView("index");
    }

    @GetMapping("Login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("Signup.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("Login")
    public RedirectView loginProcess(@RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session) {
        User dbUser = this.userRepository.findByEmail(email);
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbUser.getPassword());
        if (isPasswordMatched) {
            session.setAttribute("email", dbUser.getEmail());
            session.setAttribute("user_id", dbUser.getId());
            return new RedirectView("index");
        } else
            return new RedirectView("Registration");
    }

    @GetMapping("doctors")
    public ModelAndView getDoctors() {
        ModelAndView mav = new ModelAndView("/user/doctors.html");
        List<Doctor> doctors = doctorRepository.findAll();
        mav.addObject("doctors", doctors);
        return mav;
    }
}