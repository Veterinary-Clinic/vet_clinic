package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

@RestController
@RequestMapping("/Pet")
public class PetController {
    
    private  PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("addPet")
    public ModelAndView addPet() {
        ModelAndView mav = new ModelAndView("/user/addPet.html");
        mav.addObject("pet", new Pet());
        return mav;
    }
    
    @PostMapping("addPet")
    public String savePet(@ModelAttribute Pet npet) {
        this.petRepository.save(npet);
        return "added";
    }
}
