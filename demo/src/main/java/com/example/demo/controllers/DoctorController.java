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

    // Add attributes to the ModelAndView
    mav.addObject("name", name);
    mav.addObject("email", email);
    mav.addObject("phonenumber", phonenumber);
    mav.addObject("id", id); // Add doctor's ID to the ModelAndView

    return mav;
}
 



@GetMapping("/editProfile")
public ModelAndView editProfilePage(HttpSession session) {
    ModelAndView mav = new ModelAndView("doctors/EditProfile"); // Assuming "doctors/EditProfile" is the correct template name
    Doctor doctor = (Doctor) session.getAttribute("doctor"); // Retrieve the doctor object from session
    mav.addObject("doctor", doctor); // Add the doctor object to the ModelAndView
    return mav;
}

@PostMapping("/editProfile")
public RedirectView editProfile(@ModelAttribute Doctor updatedDoctor,
                                @RequestParam(value = "newPassword", required = false) String newPassword,
                                HttpSession session) {
    Doctor doctor = (Doctor) session.getAttribute("doctor"); // Retrieve the doctor object from session
    if (doctor != null) {
        // Update doctor's information
        doctor.setName(updatedDoctor.getName());
        doctor.setEmail(updatedDoctor.getEmail());
        
        // Update password if provided
        if (newPassword != null && !newPassword.isEmpty()) {
            String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            doctor.setPassword(encryptedPassword);
        }

        // Save the updated doctor to the session
        session.setAttribute("doctor", doctor);

        // Redirect to the profile page
        return new RedirectView("/Profile");
    } else {
        // Handle case where doctor is not found in session
        // You can redirect to an error page or handle it as per your application's requirement
        return new RedirectView("/error");
    }
}





@GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return new RedirectView("/doctor/login");
}
}