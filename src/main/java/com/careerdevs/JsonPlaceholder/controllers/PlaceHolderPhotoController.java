package com.careerdevs.JsonPlaceholder.controllers;

import com.careerdevs.JsonPlaceholder.models.Photo;
import com.careerdevs.JsonPlaceholder.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
// TODO: POST -- DONE
// TODO: PUT -- DONE
// TODO: DELETE -- DONE

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

        String tempUrl = JPH_URL + "photos/";
        ArrayList<Photo> photos = new ArrayList<>();
        int begUserId = Integer.parseInt(startId);
        int endUserId = Integer.parseInt(endId);

        try {
            for (int i = begUserId; i <= endUserId ; i++) {
                photos.add(restTemplate.getForObject(tempUrl + i, Photo.class));
            }
        } catch (Exception exc) {
            return exc.getMessage();
        }

        return photos;
    }

    @PostMapping("/photos")
    public Object postPhoto(RestTemplate restTemplate,
                          @RequestBody Photo photo) {
        Photo newPhoto;
        String tempUrl = JPH_URL + "photos/";

        try {
            newPhoto = restTemplate.postForObject(tempUrl, photo, Photo.class);
        } catch (Exception exc) {
            System.out.println("Error occurred: " + exc.getMessage());
            return exc.getMessage();
        }

        return newPhoto;

    }

    @PutMapping("/photos/{id}")
    public Object putPhoto(RestTemplate restTemplate,
                           @PathVariable(name = "id") String id,
                           @RequestBody Photo photo) {
        String tempUrl = JPH_URL + "photos/" + id;

        try {
            restTemplate.put(tempUrl, photo);
            return "Updated photo " + id;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return exc.getMessage();
        }
    }

    @DeleteMapping("/photos/{id}")
    public Object deletePhoto(RestTemplate restTemplate,
                              @PathVariable(name = "id") String id){
        String tempUrl = JPH_URL + "photos/" + id;

        try {
            restTemplate.delete(tempUrl);
            return "Successfully deleted photo " + id;
        } catch (HttpClientErrorException.NotFound exc) {
            return "ID did not a match a user in the database";
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return exc.getMessage();
        }


    }
}
