package ru.kata.spring.boot_security.demo.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
private final UserServiceImpl userService;
private final RoleServiceImpl roleService;
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUserList(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        List<User> userList = userService.getUsers();
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("users",userList);
        model.addAttribute("roles",roleList);
        model.addAttribute("user",user);// внес изменения. что бы получить текующую роль

        return "admin";
    }
    @GetMapping("/signup")
    public String showUserSignFrom( Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles",roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "add-user";

    }
    @PostMapping("/adduser")
    public String addUser(User user,@NotNull Model model) {
        model.addAttribute("user", user);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, @NotNull Model model) {
        User user = userService.findUserById(id);
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles",roleList);
        return "redirect:/admin";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

