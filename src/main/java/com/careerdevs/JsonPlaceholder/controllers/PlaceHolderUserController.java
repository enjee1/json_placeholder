package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
            users = restTemplate.getForObject(JPH_URL + "users", User[].class);

        } catch (Exception exc) {
            return exc.getMessage();
        }

        return users;
    }

    @GetMapping("/users/{id}")
    public Object getUserById(RestTemplate restTemplate,
                              @PathVariable(name = "id") String id) {
        User user;
        String tempUrl = JPH_URL + "users/" + id;
        try {
            user = restTemplate.getForObject(tempUrl, User.class);
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return user;
    }

    @GetMapping("/usersbyrange")
    public Object getUsersByRange(RestTemplate restTemplate,
                                  @RequestParam(name = "start") String startId,
                                  @RequestParam(name = "end") String endId) {


        ArrayList<User> users = new ArrayList<>();
        int begUserId = Integer.parseInt(startId);
        int endUserId = Integer.parseInt(endId);

        try {
            for (int i = begUserId; i <= endUserId; i++) {
                users.add(restTemplate.getForObject(JPH_URL + "users/" + i, User.class));
            }
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return users;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(RestTemplate restTemplate,
                                 @PathVariable(name = "id") String id) {
        String tempUrl = JPH_URL + "users/" + id;

        try {
            restTemplate.delete(tempUrl);
        } catch (HttpClientErrorException.NotFound exc) {
            return "ID did not a match a user in the database";
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return exc.getMessage();
        }

        return "Successfully deleted user " + id;
    }

    @PostMapping("/users")
    public Object postUser(RestTemplate restTemplate,
                            @RequestBody User user) {

        String tempUrl = JPH_URL + "users/";

        return restTemplate.postForObject(tempUrl, user, User.class);
    }

    @PutMapping("/users/{id}")
    public Object putUser(
            RestTemplate restTemplate,
            @PathVariable(name = "id") String id,
            @RequestBody User user) {

        String tempUrl = JPH_URL + "users/" + id;
        try {
            restTemplate.put(tempUrl, user);
            return "Updated user " + id;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return exc;
        }

    }
}


