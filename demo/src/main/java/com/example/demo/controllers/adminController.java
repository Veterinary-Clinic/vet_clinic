package com.example.demo.controllers;

import com.example.demo.models.admin;
import com.example.demo.models.doctor;
import com.example.demo.repositories.adminRepository;
import com.example.demo.repositories.doctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private adminRepository adminRepository;
    private doctorRepository doctorRepository;

    @GetMapping("")
    public String listAdmins(Model model) {
        model.addAttribute("admin", adminRepository.findAll());
        return "admin/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("admin", new admin());
        return "admin/addAdmin";
    }

    @PostMapping("/add")
public String addDoctor(@ModelAttribute("admin") admin admin) {
    adminRepository.save(admin);
    return "redirect:/admin";
}

    @GetMapping("/{id}/edit")
    public String showEditAdminForm(@PathVariable("id") Long id, Model model) {
        admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        model.addAttribute("admin", admin);
        return "doctors/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAdmin(@PathVariable("id") Long id, @ModelAttribute("admin") admin updatedAdmin) {
        admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin ID: " + id));
        admin.setUsername(updatedAdmin.getUsername());
       
        adminRepository.save(admin);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteAdmin(@PathVariable("id") Long id) {
        admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
    adminRepository.delete(admin);
        return "redirect:/admin";
    }


   

}
