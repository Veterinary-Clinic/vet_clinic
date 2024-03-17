package com.example.demo.controllers;

import com.example.demo.models.Doctor;
import com.example.demo.models.User;
import com.example.demo.repositories.DoctorRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctors/list";
    }

    @GetMapping("signup")
    public ModelAndView addDoctor() {
        ModelAndView mav = new ModelAndView("/doctors/HomePage.html");
        Doctor newDoctor = new Doctor();
        mav.addObject("doctor", newDoctor);
        return mav;
    }

    @PostMapping("signup")
    public RedirectView saveDoctor(@ModelAttribute Doctor doctor) {
        String encodedPassword = BCrypt.hashpw(doctor.getPassword(), BCrypt.gensalt(12));
        doctor.setPassword(encodedPassword);
        doctorRepository.save(doctor);
        return new RedirectView("login");
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/doctors/loginDoctor.html");
        mav.addObject("doctor", new Doctor());
        return mav;
    } 

    @PostMapping("/login")
public RedirectView loginProgress(@RequestParam("name") String name,
        @RequestParam("password") String password, HttpSession session) {

    Doctor dbDoctor = this.doctorRepository.findByname(name);
    if (dbDoctor != null && BCrypt.checkpw(password, dbDoctor.getPassword())) {
        session.setAttribute("name", dbDoctor.getName());
        session.setAttribute("email", dbDoctor.getEmail());
        session.setAttribute("phonenumber", dbDoctor.getPhonenumber());
        session.setAttribute("id", dbDoctor.getId()); // Add id to session attributes
        return new RedirectView("/doctor/index"); // Redirect to the appropriate page
    } else {
        return new RedirectView("/doctor/login"); // Redirect to login page with error flag
    }
}


    @GetMapping("index")
    public ModelAndView gethome() {
        ModelAndView mav = new ModelAndView("/doctors/index.html");
        return mav;
    }

    // @GetMapping("/Profile")
    // public ModelAndView getProfile() {
    //     ModelAndView mav = new ModelAndView("/doctors/ProfileDoctor");
    //     return mav;
    // }
    @GetMapping("/Profile")
public ModelAndView viewProfile(HttpSession session) {
    Doctor doctor = new Doctor(); // Assuming you have a way to retrieve the logged-in doctor
    ModelAndView mav = new ModelAndView("/doctors/ProfileDoctor.html");

    // Retrieve attributes from session or doctor object
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email"); // Retrieve email from session or doctor object
    String phonenumber = (String) session.getAttribute("phonenumber"); // Retrieve phonenumber from doctor object
    Long id = (Long) session.getAttribute("id"); // Retrieve doctor's ID from session
    String password = (String) session.getAttribute("password");
    // Add attributes to the ModelAndView
    mav.addObject("name", name);
    mav.addObject("email", email);
    mav.addObject("password", password);
    mav.addObject("phonenumber", phonenumber);
    mav.addObject("id", id); // Add doctor's ID to the ModelAndView

    return mav;
}
 
   
@GetMapping("/editProfile")
public ModelAndView editDoctor(HttpSession session) {
    ModelAndView mav = new ModelAndView("doctors/editProfile.html");
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phonenumber = (String) session.getAttribute("phonenumber");
    String password = (String) session.getAttribute("password");
    mav.addObject("name", name);
    mav.addObject("email", email);
    mav.addObject("phonenumber", phonenumber);
    mav.addObject("password", password);
    Doctor dr=doctorRepository.findByName(name);
    mav.addObject("doctor", dr);
    return mav;
}

@PostMapping("/editProfile")
public RedirectView editProfile(@ModelAttribute Doctor updatedDoctor,
                                @RequestParam(value = "newPassword", required = false) String newPassword,
                                HttpSession session) {
    String name = (String) session.getAttribute("name");
    Doctor existingUser = doctorRepository.findByName(name);

    if (existingUser != null) {
        existingUser.setName(updatedDoctor.getName());
        existingUser.setEmail(updatedDoctor.getEmail());
        
        // Check if a new password is provided and update it
        if (newPassword != null && !newPassword.isEmpty()) {
            // Don't hash the password if you want to store it as plain text
            existingUser.setPassword(newPassword);
        }

        doctorRepository.save(existingUser);

        // Update session attribute if necessary
        session.setAttribute("name", existingUser.getName());
        session.setAttribute("email", existingUser.getEmail());
        session.setAttribute("password", existingUser.getPassword());
    }

    return new RedirectView("/doctor/Profile");
}




@GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return new RedirectView("/doctor/login");
}
}