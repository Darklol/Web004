package ru.itmo.lab4.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.lab4.model.User;
import ru.itmo.lab4.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository repository;


    @Override
    public Optional<User> findById(Long id) {
        log.info("find by id " + id);
        return repository.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        log.info("findByLogin " + login);
        return repository.findByLogin(login);
    }

    @Override
    public void save(User user) {
        repository.save(user);
        log.info("saved " + user);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll()");
        return repository.findAll();
    }

    @Override
    public User get(Long id) {
        log.info("get " + id);
        return repository.getOne(id);
    }

    @Override
    public void update(User user) {
        log.info("update " + user);
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        log.info("delete " + id);
        repository.deleteById(id);
    }
}
