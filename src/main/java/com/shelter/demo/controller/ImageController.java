package com.shelter.demo.controller;

import com.shelter.demo.model.Image;
import com.shelter.demo.repo.ImageRepository;
import com.shelter.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/animals")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestParam MultipartFile image,
                                  @RequestParam Long animalId) throws IOException {

        Image newImage = imageService.saveImage(image.getBytes(), animalId);
        // TODO return path instead of whole object
        return new ResponseEntity<Image>(newImage, HttpStatus.OK);

    }

}
