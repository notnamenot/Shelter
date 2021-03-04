package com.shelter.demo.service;

import com.shelter.demo.repo.AnimalRepository;
import com.shelter.demo.exception.AnimalNotFoundException;
import com.shelter.demo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Service / Business logic layer
// class has to be instantiated so it has to be a spring bean
@Service //@Component
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public Animal findAnimalById(Long animalId) {
        return animalRepository.findAnimalById(animalId)
                    .orElseThrow(() -> new AnimalNotFoundException(animalId));
    }

    public Animal addNewAnimal(Animal animal) {
        Optional<Animal> animalOptional = animalRepository.findAnimalByName(animal.getName());
        if (animalOptional.isPresent()) {
            throw new IllegalStateException("Name taken!");
        }
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
        if (! animalRepository.existsById(animalId)) {
              throw new AnimalNotFoundException(animalId);
        }
        animalRepository.deleteById(animalId);
    }

//    @Transactional // entity goes into managed state
//    public Animal updateAnimal(Long animalId, String name, String descr) {
//        Animal animal = animalRepository.findById(animalId)
//                .orElseThrow(() -> new AnimalNotFoundException(animalId));
//
//        if (descr != null && descr.length() > 0 && !Objects.equals(descr, animal.getDescr())) {
//            animal.setDescr(descr);
//        }
//        if (name != null && name.length() > 0 && !Objects.equals(name, animal.getName())) {
//            if (animalRepository.findAnimalByName(name).isPresent()) {
//               throw new IllegalStateException("Name taken!");
//            }
//            animal.setName(name);
//        }
//        return animal;
//    }

    @Transactional // entity goes into managed state
    public Animal updateAnimal(Animal updatedAnimal) {
        Animal animal = animalRepository.findById(updatedAnimal.getId())
                .orElseThrow(() -> new AnimalNotFoundException(updatedAnimal.getId()));

        if (animal.getDescr() != null && animal.getDescr().length() > 0 && !Objects.equals(updatedAnimal.getDescr(), animal.getDescr())) {
            animal.setDescr(updatedAnimal.getDescr());
        }

        if (updatedAnimal.getName() != null && updatedAnimal.getName().length() > 0 && !Objects.equals(updatedAnimal.getName(), animal.getName())) {
            if (animalRepository.findAnimalByName(updatedAnimal.getName()).isPresent()) {
                throw new IllegalStateException("Name taken!");
            }
            animal.setName(updatedAnimal.getName());
        }

        return animal;
    }
}
