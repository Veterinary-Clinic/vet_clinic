package com.example.demo.controllers;

import com.example.demo.models.Doctor;
import com.example.demo.models.User;
import com.example.demo.repositories.DoctorRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctors/list";
    }

    @GetMapping("/signup")
    public ModelAndView addDoctor() {
        ModelAndView mav = new ModelAndView("/doctors/HomePage");
        Doctor newDoctor = new Doctor();
        mav.addObject("doctor", newDoctor);
        return mav;
    }

    @PostMapping("/signup")
    public RedirectView saveDoctor(@ModelAttribute Doctor doctor) {
        String encodedPassword = BCrypt.hashpw(doctor.getPassword(), BCrypt.gensalt(12));
        doctor.setPassword(encodedPassword);
        doctorRepository.save(doctor);
        return new RedirectView("login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/doctors/loginDoctor");
        mav.addObject("doctor", new Doctor());
        return mav;
    }

    @PostMapping("/login")
    public RedirectView loginProgress(@RequestParam("name") String name,
            @RequestParam("password") String password, HttpSession session) {
        Doctor dbDoctor = doctorRepository.findByname(name);
        if (dbDoctor != null && BCrypt.checkpw(password, dbDoctor.getPassword())) {
            session.setAttribute("name", dbDoctor.getName());
            return new RedirectView("index");
        } else {
            // return "failed";
            return new RedirectView("login");
        }
    }

    @GetMapping("/index")
    public ModelAndView gethome() {
        ModelAndView mav = new ModelAndView("/doctors/index");
        return mav;
    }

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
