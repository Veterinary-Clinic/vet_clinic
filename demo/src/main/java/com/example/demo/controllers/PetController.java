package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

@RestController

public class PetController {
    @Autowired
    private  PetRepository petRepository;


    /*@GetMapping("pets")
    public ModelAndView getpets() {
        ModelAndView mav = new ModelAndView("/user/pets.html");
        List<Pet>pets = this.petRepository.findAll();
        mav.addObject("pets", pets);
        return mav;
    }

    @GetMapping("addPet")
    public ModelAndView addPet() {
        ModelAndView mav = new ModelAndView("/user/addPet.html");
        mav.addObject("pet", new Pet());
        return mav;
    }
    
    @PostMapping("addPet")
    public RedirectView savePet(@ModelAttribute Pet npet) {
        petRepository.save(npet);
        return new RedirectView("pets");
    }
    @GetMapping("/deletePet/{id}")
    public RedirectView deletePet(@PathVariable("id") Long id) {
        petRepository.deleteById(id);
        return new RedirectView("pets");
    }*/
}
