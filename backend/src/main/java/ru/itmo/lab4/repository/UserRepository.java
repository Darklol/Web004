package ru.itmo.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.lab4.model.User;

public interface UserRepository extends JpaRepository<User, Long > {
    User findByLogin(String login);
}
