package com.example.demo.controllers;

import com.example.demo.models.Doctor;
import com.example.demo.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Doctor;
import com.example.demo.repositories.DoctorRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

=======
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
>>>>>>> 2c26dfb8ce9a0c8aa814f1e6ec9672b5c51e6fd1

@Controller
@RequestMapping("/admin/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    // @GetMapping("")
    // public String listDoctors(Model model) {
    //     model.addAttribute("doctors", doctorRepository.findAll());
    //     return "doctors/list";
    // }

    // @GetMapping("/add")
    // public String showAddForm(Model model) {
    //     model.addAttribute("doctor", new Doctor());
    //     return "doctors/add";
    // }

    //     @PostMapping("/add")
    // public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
    //     doctorRepository.save(doctor);
    //     return "redirect:/admin/doctors";
    // }

    // @GetMapping("/{id}/edit")
    // public String showEditForm(@PathVariable("id") Long id, Model model) {
    //     Doctor doctor = doctorRepository.findById(id)
    //             .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    //     model.addAttribute("doctor", doctor);
    //     return "doctors/edit";
    // }

    // @PostMapping("/{id}/edit")
    // public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("doctor") Doctor updatedDoctor) {
    //     Doctor doctor = doctorRepository.findById(id)
    //             .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    //     doctor.setName(updatedDoctor.getName());
       
    //     doctorRepository.save(doctor);
    //     return "redirect:/admin/doctors";
    // }

    @GetMapping("/index")
    public ModelAndView gethome() {
        ModelAndView mav = new ModelAndView("/doctors/index");
        return mav;
    }

    // @GetMapping("/Profile")
    // public ModelAndView getProfile() {
    //     ModelAndView mav = new ModelAndView("/doctors/ProfileDoctor");
    //     return mav;
    // }
    @GetMapping("/Profile")
public ModelAndView viewProfile(HttpSession session) {
    ModelAndView mav = new ModelAndView("/doctors/ProfileDoctor");
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phonenumber = (String) session.getAttribute("phonenumber");
    mav.addObject("name",name);
    mav.addObject("email",email);
    mav.addObject("phonenumber",phonenumber); 
    return mav;
}


    // @GetMapping("/editProfile")
    // public ModelAndView editProfile(HttpSession session) {
    //     ModelAndView mav = new ModelAndView("editprofile");
    //     String username = (String) session.getAttribute("username");
    //     user user = userRepository.findByUsername(username);
    //     mav.addObject("user", user);
    //     return mav;
    // }

    // @PostMapping("/editProfile")
    // public RedirectView editProfile(@ModelAttribute user updatedUser,
    //         @RequestParam(value = "newPassword", required = false) String newPassword,
    //         HttpSession session) {
    //     String username = (String) session.getAttribute("username");
    //     user existingUser = userRepository.findByUsername(username);

    //     if (existingUser != null) {
    //         existingUser.setUsername(updatedUser.getUsername());
    //         existingUser.setDob(updatedUser.getDob());
    //         if (newPassword != null && !newPassword.isEmpty()) {
    //             String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
    //             existingUser.setPassword(encryptedPassword);
    //         }

    //         userRepository.save(existingUser);

    //         session.setAttribute("username", existingUser.getUsername());
    //     }

    //     return new RedirectView("Profile");
    // }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/add";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        model.addAttribute("doctor", doctor);
        return "doctors/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("doctor") Doctor updatedDoctor) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        doctor.setName(updatedDoctor.getName());

        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable("id") Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        doctorRepository.delete(doctor);
        return "redirect:/admin/doctors";
    }
}