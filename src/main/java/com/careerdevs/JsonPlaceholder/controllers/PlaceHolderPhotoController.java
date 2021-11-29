package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.Photo;
import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class PlaceHolderPhotoController {
    private static final String JPH_URL = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/photos")
    public Object getAllPhotos(RestTemplate restTemplate) {
        Photo[] allPhotos;

        try {
            allPhotos = restTemplate.getForObject(JPH_URL + "photos/", Photo[].class);

        } catch (Exception exc) {
            return exc.getMessage();
        }

        return allPhotos;
    }

    @GetMapping("/photos/{id}")
    public Object getPhotoById(RestTemplate restTemplate,
                               @PathVariable(name = "id") String id) {
        Photo photo;

        try {
            photo = restTemplate.getForObject(JPH_URL + "photos/" + id, Photo.class);
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return photo;
    }
}
