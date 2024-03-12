package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestParam;
// import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.admin;
import com.example.demo.models.doctor;
import com.example.demo.repositories.adminRepository;
import com.example.demo.repositories.doctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private doctorRepository doctorRepository ;
    //   @GetMapping("index")
    //     public ModelAndView getDoctors() {
    //         ModelAndView mav = new ModelAndView("/admin/index.html");
    //         admin newAdmin =new admin();
    //         mav.addObject("admin",newAdmin);
    //         return mav;
    //     }
//         @GetMapping("addDoctor")
//         public ModelAndView addDoctor() {
//             ModelAndView mav = new ModelAndView("/admin/addDoctor.html");
//             admin newAdmin =new admin();
//             mav.addObject("admin", newAdmin);
//             return mav;
//         }
//         @PostMapping("addDoctor")
//         public String saveDoctor(@ModelAttribute doctor doctor){

//             String encoddedPassword=BCrypt.hashpw(doctor.getPassword(), BCrypt.gensalt(12));
//             doctor.setPassword(encoddedPassword);
//             this.doctorRepository.save(doctor);
// return "added";
//         }



        // @PostMapping("/admin/addDoctor")
        // public String saveAdmin(@ModelAttribute doctor newDoctor) {
        //     String encodedPassword = newDoctor.getPassword();
        //     newDoctor.setPassword(encodedPassword);
        //     doctorRepository.save(newDoctor);
        //     return "redirect:/admin";
        // }
    
}
