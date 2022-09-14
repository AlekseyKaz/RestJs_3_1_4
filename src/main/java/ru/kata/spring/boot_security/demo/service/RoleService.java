package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

public interface RoleService  {
    Role getRoleById(long id);

    void addRole(Role role);

    List<Role> getRoles();
}
