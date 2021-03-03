package com.shelter.demo.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(Long animalId) {
        super("Animal with id " + animalId + " does not exist!");
    }
}
