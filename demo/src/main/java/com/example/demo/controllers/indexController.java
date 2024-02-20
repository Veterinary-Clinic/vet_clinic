package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class indexController {

    @GetMapping("/user/index")
    public String getIndex() {
        return "user/index"; // Return the name of the view template without file extension or path
    }

}
