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

    @GetMapping("")
    public String indexAdmins(Model model) {
        // model.addAttribute("admin", adminRepository.findAll());
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

            else{
        adminRepository.save(admin);
        return "redirect:/admin/list" ; 
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

        Admin dbAdmin = this.adminRepository.findByUsername(name);
        if (dbAdmin!= null &&BCrypt.checkpw(password, dbAdmin.getPassword())) {
            // session.setAttribute("name", dbDoctor.getName());
            // session.setAttribute("email", dbDoctor.getEmail());
            // session.setAttribute("phonenumber", dbDoctor.getPhonenumber());
            return new RedirectView("index");
        } else {
            return new RedirectView("login");
        }
    }
    @GetMapping("/Profile")
    public ModelAndView viewProfile(HttpSession session) {
        Admin admin = new Admin(); // Assuming you have a way to retrieve the logged-in doctor
        ModelAndView mav = new ModelAndView("/admin/profileAdminhtml");
    
        // Retrieve attributes from session or doctor object
        String name = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password"); // Retrieve email from session or doctor object
        // String phonenumber =(String) session.getAttribute("phonenumber"); // Retrieve phonenumber from doctor object
    
        // // Add attributes to the ModelAndView
        // mav.addObject("name", name);
        // mav.addObject("email", email);
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
    public String updateAdmin(@Valid @PathVariable("id") Long id, @ModelAttribute("Admin") Admin updatedAdmin , BindingResult bindingResult) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        admin.setUsername(updatedAdmin.getUsername());
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/edit";
        }else{
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


    //doctor 
    @GetMapping("/doctorList")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "Admin/doctorList";
    }

    @GetMapping("/addDoctor")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "Admin/addDoctor";
    }

        @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute("Doctor") Doctor doctor) {
       this.doctorRepository.save(doctor);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editDoctor")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        model.addAttribute("doctor", doctor);
        return "admin/doctorEdit";
    }

    @PostMapping("/{id}/editDoctor")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("Doctor") Doctor updatedDoctor) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        doctor.setName(updatedDoctor.getName());
       
        doctorRepository.save(doctor);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/deleteDoctor")
    public String deleteDoctor(@PathVariable("id") Long id) {
        
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        doctorRepository.delete(doctor);
        return "redirect:/admin";
    }
    @ExceptionHandler
    public String handleValidationException(Exception exception) {
        return "admin/validation-error";
    }

    @ModelAttribute("admin")
    public Admin getAdminModelAttribute() {
        return new Admin();
    }
}
