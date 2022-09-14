package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
private final UserService userService;
private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


//    @GetMapping("/users")
//    public String showUserList(Model model,Principal principal) {
//        User user = userService.findByEmail(principal.getName());
//        return "áddasda " + principal.getName();
//    }
//    @GetMapping("/user")
//    public String user(Model model,Principal principal) {
//        User user = userService.findByEmail(principal.getName());
//        model.addAttribute("user", user);
//        return "/user";
//    }
    @GetMapping("/user")
    public String index(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        List<User> users = userService.getUsers();
        List role = roleService.getRoles();
        model.addAttribute("users",user);
        model.addAttribute("role",role);
        model.addAttribute("usersList",users);
        return "user";
    }
}
