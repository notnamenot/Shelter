package com.shelter.demo.repo;

import com.shelter.demo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Data access Layer
// Responsible for DB access (JPA)
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // select * from animals where name = ?
    // query is optional - naming convention would be enough
    @Query("SELECT a from Animal a WHERE a.name = ?1")
    Optional<Animal> findAnimalByName(String name);

    Optional<Animal> findAnimalById(Long animalId); // equals to default findById
}
