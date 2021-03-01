package com.shelter.demo.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Data access Layer
// Responsible for DB access (JPA)
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
