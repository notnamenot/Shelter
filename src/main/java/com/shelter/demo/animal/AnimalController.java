package com.shelter.demo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        animalService.addNewAnimal(animal);
    }

    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long animalId ){
        animalService.deleteAnimal(animalId);
    }

    @PutMapping(path = "{animalId}")
    public void updateAnimal(@PathVariable("animalId") Long animalId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String descr) {
        animalService.updateAnimal(animalId, name, descr);
    }

}
