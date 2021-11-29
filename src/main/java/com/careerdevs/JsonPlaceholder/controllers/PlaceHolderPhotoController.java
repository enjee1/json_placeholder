package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.Photo;
import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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

    @GetMapping("/photosbyrange")
    public Object getPhotosByRange(RestTemplate restTemplate,
                                  @RequestParam(name = "start") String startId,
                                  @RequestParam(name = "end") String endId){

        ArrayList<Photo> photos = new ArrayList<>();
        int begUserId = Integer.parseInt(startId);
        int endUserId = Integer.parseInt(endId);

        try {
            for (int i = begUserId; i <= endUserId ; i++) {
                photos.add(restTemplate.getForObject(JPH_URL + "photos/" + i, Photo.class));
            }
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return photos;
    }
}
