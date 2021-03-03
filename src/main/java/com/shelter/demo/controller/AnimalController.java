package com.shelter.demo.controller;

import com.shelter.demo.model.Animal;
import com.shelter.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// API Layer
// Class to hold resources for API
// Controller passes requests over to the server
@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired // to instantiate animalService and inject it into constructor
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAnimals() {
        List<Animal> animals = animalService.getAnimals();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping(path = "/{animalId}")
    public ResponseEntity<Animal> getAnimal(@PathVariable("animalId") Long animalId) {
        Animal animal = animalService.findAnimalById(animalId);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        Animal newAnimal = animalService.addNewAnimal(animal);
        return new ResponseEntity<>(newAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long animalId ){
        animalService.deleteAnimal(animalId);
    }

    @PutMapping(path = "{animalId}") // TODO update with @RequestBody?
    public ResponseEntity<Animal> updateAnimal(@PathVariable("animalId") Long animalId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String descr) {
        Animal animal = animalService.updateAnimal(animalId, name, descr);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

}
