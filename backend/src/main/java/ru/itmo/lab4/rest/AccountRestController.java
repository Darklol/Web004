package ru.itmo.lab4.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.lab4.model.User;
import ru.itmo.lab4.service.UsersServiceImpl;

import javax.validation.Valid;

@RestController
@Slf4j
public class AccountRestController {

    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        log.info(user.toString());
        log.info("login " + user.getLogin());
        if (usersService.findByLogin(user.getLogin()) != null) {
            log.error("username already exists " + user.getLogin());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        log.info("user registered " + user.getLogin());
        user.setHashPass(passwordEncoder.encode(user.getHashPass()));
        if (user.getId() == 0){
            user.setId(null); // ahahah
        }
        usersService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid User user){
        User u = usersService.findByLogin(user.getLogin());
        if (u==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
