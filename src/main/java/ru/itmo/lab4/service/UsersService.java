package ru.itmo.lab4.service;

import ru.itmo.lab4.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Optional<User> findById(Long id);
    User findByLogin(String login);
    void save(User user);
    List<User> getAll();
    User get(Long id);
    void update(User user);
    void delete(Long id);
}
