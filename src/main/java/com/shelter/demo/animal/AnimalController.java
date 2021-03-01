package com.shelter.demo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// API Layer
// Class to hold resources for API
@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired // to instantiate animalService and inject it into constructor
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }
}
