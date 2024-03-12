package com.example.demo.controllers;

import com.example.demo.models.doctor;
import com.example.demo.repositories.doctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/doctors")
public class doctorController {
    @Autowired
    private doctorRepository doctorRepository;

    @GetMapping("")
    public String listDoctors(Model model) {
        model.addAttribute("doctor", doctorRepository.findAll());
        return "doctors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new doctor());
        return "doctors/add";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute("doctor") doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        model.addAttribute("doctor", doctor);
        return "doctors/edit";
    }

    @PostMapping("/{id}/edit")
    public String processEditForm(@PathVariable("id") Long id, @ModelAttribute("doctor") @Valid doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return "doctors/edit";
        }

        doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + id));
        existingDoctor.setName(doctor.getName());
        
        doctorRepository.save(existingDoctor);

        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }
}
