package com.shelter.demo.animal;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
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

    public void addNewAnimal(Animal animal) {
        Optional<Animal> animalOptional = animalRepository.findAnimalByName(animal.getName());
        if (animalOptional.isPresent()) {
            throw new IllegalStateException("Name taken!");
        }
        animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
        if (! animalRepository.existsById(animalId)) {
              throw new IllegalStateException(
                      "Animal with id " + animalId + " does not exist!"
              );
        }
        animalRepository.deleteById(animalId);
    }

    @Transactional // entity goes into managed state
    public void updateAnimal(Long animalId, String name, String descr) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalStateException("Animal with id " + animalId + " does not exist!"));

        if (descr != null && descr.length() > 0 && !Objects.equals(descr, animal.getDescr())) {
            animal.setDescr(descr);
        }
        if (name != null && name.length() > 0 && !Objects.equals(name, animal.getName())) {
            if (animalRepository.findAnimalByName(name).isPresent()) {
               throw new IllegalStateException("Name taken!");
            }
            animal.setName(name);
        }
    }
}
