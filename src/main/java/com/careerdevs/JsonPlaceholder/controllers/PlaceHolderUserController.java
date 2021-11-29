package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
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
    public Object getUserById(RestTemplate restTemplate,
                                @PathVariable(name = "id") String id) {
        User user;

        try {
            user = restTemplate.getForObject(JPH_URL + "users/" + id, User.class);
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return user;
    }

    @GetMapping("/usersbyrange")
    public Object getUsersByRange(RestTemplate restTemplate,
                                  @RequestParam(name = "start") String startId,
                                  @RequestParam(name = "end") String endId){

        ArrayList<User> users = new ArrayList<>();
        int begUserId = Integer.parseInt(startId);
        int endUserId = Integer.parseInt(endId);

        try {
            for (int i = begUserId; i <= endUserId ; i++) {
                users.add(restTemplate.getForObject(JPH_URL + "users/" + i, User.class));
            }
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return users;
    }
}
