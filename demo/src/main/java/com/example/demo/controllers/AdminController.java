package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.models.Doctor;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.DoctorRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/index")
    public String indexAdmins(Model model) {
        return "admin/index";
    }

    @GetMapping("/list")
    public String listAdmins(Model model) {
        model.addAttribute("admins", adminRepository.findAll());
        return "admin/list";
    }

    @GetMapping("/addAdmin")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@Validated @ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addAdmin";
        }

        else {
            String encodedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(12));
            admin.setPassword(encodedPassword);
            adminRepository.save(admin);
            return "redirect:/admin/list";
        }
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/admin/loginAdmin.html");
        mav.addObject("admin", new Admin());
        return mav;
    }

    @PostMapping("login")
    public RedirectView loginProgress(@RequestParam("username") String name,
            @RequestParam("password") String password, HttpSession session) {

        Admin dbAdmin = this.adminRepository.findByusername(name);
        // Admin dbAdminPassword = this.adminRepository.findBypassword(password);
        if (dbAdmin != null && BCrypt.checkpw(password, dbAdmin.getPassword())) {
            session.setAttribute("username", dbAdmin.getUsername());
            // session.setAttribute("email", dbDoctor.getEmail());
            // session.setAttribute("phonenumber", dbDoctor.getPhonenumber());
            return new RedirectView("index");
        } else {
            return new RedirectView("login");
        }
    }

    @GetMapping("/profile")
    public ModelAndView viewProfile(HttpSession session) {
        Admin admin = new Admin(); // Assuming you have a way to retrieve the logged-in doctor
        ModelAndView mav = new ModelAndView("/admin/profileAdmin.html");

        // Retrieve attributes from session or doctor object
        String name = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password"); // Retrieve email from session or doctor object
        // String phonenumber =(String) session.getAttribute("phonenumber"); // Retrieve
        // phonenumber from doctor object

        // // Add attributes to the ModelAndView
        mav.addObject("username", name);
        mav.addObject("password", password);
        // mav.addObject("phonenumber", phonenumber);

        return mav;
    }

    @GetMapping("/{id}/editAdmin")
    public String showEditAdminForm(@PathVariable("id") Long id, Model model) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("admin", admin);
        return "admin/edit";
    }

    @PostMapping("/{id}/editAdmin")
    public String updateAdmin(@Valid @PathVariable("id") Long id, @ModelAttribute("Admin") Admin updatedAdmin,
            BindingResult bindingResult) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        admin.setUsername(updatedAdmin.getUsername());
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/edit";
        } else {
            adminRepository.save(admin);
            return "redirect:/admin/list";
        }
    }

    @PostMapping("/{id}/deleteAdmin")
    public String deleteAdmin(@PathVariable("id") Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        adminRepository.delete(admin);
        return "redirect:/admin/list";
    }

    // doctor
    @GetMapping("/doctorList")
    public String listDoctors(Model model) {
        model.addAttribute("Doctor", doctorRepository.findAll());
        return "admin/doctorList";
    }


    @GetMapping("/addDoctor")
    public String showAddForm(Model model) {
        model.addAttribute("Doctor", new Doctor());
        return "Admin/addDoctor";
    }

    @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute("Doctor") Doctor doctor) {
        String encodedPassword = BCrypt.hashpw(doctor.getPassword(), BCrypt.gensalt(12));
        doctor.setPassword(encodedPassword);
        this.doctorRepository.save(doctor);
        return "redirect:/admin/doctorList";
    }

    // @PostMapping("/addDoctor")
    // public String addDoctor(@ModelAttribute("Doctor") Doctor doctor) {
    // .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    // model.addAttribute("Doctor", Doctor);
    // return "admin/doctorEdit";
    // }
    @GetMapping("/{id}/editDoctor")
    public String showEditDoctorForm(@PathVariable("id") Long id, Model model) {
        Doctor Doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("Doctor", Doctor);
        return "admin/doctorEdit";
    }

    @PostMapping("/{id}/editDoctor")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("Doctor") Doctor updatedDoctor) {
        Doctor Doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        Doctor.setName(updatedDoctor.getName());

        doctorRepository.save(Doctor);
        return "redirect:/admin/doctorList";
    }

    @PostMapping("/{id}/deleteDoctor")
    public String deleteDoctor(@PathVariable("id") Long id) {

        Doctor Doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        doctorRepository.delete(Doctor);
        return "redirect:/admin/doctorList";
    }
    // @ExceptionHandler
    // public String handleValidationException(Exception exception) {
    // return "admin/validation-error";
    // }

    @GetMapping("/pets")
    public ModelAndView getProfile() {
        ModelAndView mav = new ModelAndView("/admin/pets.html");
        return mav;
    }
}
