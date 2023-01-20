package com.example.commonbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

}