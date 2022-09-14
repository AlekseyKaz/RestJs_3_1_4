package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

public interface UserService {
    User findUserById(long id);

    User findUserByEmail(String email);

    List<User> getUsers();

    void saveUser(User user);

    void editUser(User user);

    void deleteById(long id);


}
