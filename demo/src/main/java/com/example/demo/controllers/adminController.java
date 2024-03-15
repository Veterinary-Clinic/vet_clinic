package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.models.Doctor;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public String listAdmins(Model model) {
        model.addAttribute("admin", adminRepository.findAll());
        return "admin/list";
    }

    @GetMapping("/addAdmin")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@ModelAttribute("Admin") Admin admin) {
        adminRepository.save(admin);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editAdmin")
    public String showEditAdminForm(@PathVariable("id") Long id, Model model) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("admin", admin);
        return "doctors/edit";
    }

    @PostMapping("/{id}/editAdmin")
    public String updateAdmin(@PathVariable("id") Long id, @ModelAttribute("Admin") Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        admin.setUsername(updatedAdmin.getUsername());
       
        adminRepository.save(admin);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/deleteAdmin")
    public String deleteAdmin(@PathVariable("id") Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    adminRepository.delete(admin);
        return "redirect:/admin";
    }


    //doctor 
    @GetMapping("/admin/doctors/list")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctors/list";
    }

    @GetMapping("/addDoctor")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/add";
    }

        @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute("Doctor") Doctor doctor) {
       this.doctorRepository.save(doctor);
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
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("Doctor") Doctor updatedDoctor) {
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
