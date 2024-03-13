package com.example.demo.controllers;

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
    private final PetRepository petRepository = null;

    @GetMapping("addPet")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("/user/addPet.html");
        
        mav.addObject("pet", new Pet());
        return mav;
    }
    
    @PostMapping("addPet")
    public String addPet(@ModelAttribute("pet") Pet pet) {
        petRepository.save(pet);
        return "added";
    }
    
    
}


