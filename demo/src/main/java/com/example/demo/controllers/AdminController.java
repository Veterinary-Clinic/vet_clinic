package com.example.demo.controllers;

import com.example.demo.models.Admin;
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

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/addAdmin";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute("admin") Admin admin) {
        adminRepository.save(admin);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String showEditAdminForm(@PathVariable("id") Long id, Model model) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("admin", admin);
        return "doctors/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAdmin(@PathVariable("id") Long id, @ModelAttribute("admin") Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        admin.setUsername(updatedAdmin.getUsername());
       
        adminRepository.save(admin);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteAdmin(@PathVariable("id") Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    adminRepository.delete(admin);
        return "redirect:/admin";
    }


   

}