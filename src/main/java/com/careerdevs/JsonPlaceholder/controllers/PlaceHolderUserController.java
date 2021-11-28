package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PlaceHolderUserController {
    private static final String JPH_URL = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/users")
    public Object getAllUsers(RestTemplate restTemplate) {
        User[] users;

        try {
            users = restTemplate.getForObject(JPH_URL + "users/", User[].class);

        } catch (Exception exc) {
            return exc.getMessage();
        }

        return users;
    }

    @GetMapping("/users/{id}")
    public Object getSingleUser(RestTemplate restTemplate,
                                @PathVariable(name = "id") String id) {

        return  restTemplate.getForObject(JPH_URL + "users/" + id, User.class);
    }
}
