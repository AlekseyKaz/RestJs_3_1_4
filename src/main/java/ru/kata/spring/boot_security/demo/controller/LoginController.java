package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginController {

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public  String userPage() {
        return "userPage";
    }

}
