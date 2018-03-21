package com.aliaksandr.shynkevich.webserviceboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.aliaksandr.shynkevich.webserviceboot.vo.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2018-03-21
 *
 * @author Aliaksandr Shynkevich
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SimpleRestController {

    private static final String[] NAMES = {"Alex", "John", "Andry", "Kate", "Jerica", "Venkat"};

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "{ \"key\" : \"Hi Alex!!\"}";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        return generateUser(userId);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value = "amount", defaultValue = "5") Integer amount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            users.add(generateUser(UUID.randomUUID().toString()));
        }
        return users;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void createUser(@RequestBody User newUser) {
        System.out.println("Created user: " + newUser);
    }

    private User generateUser(String id) {
        return new User(id, NAMES[new Random().nextInt(6)], new Random().nextInt(100));
    }
}
