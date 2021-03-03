package com.shelter.demo;

import com.shelter.demo.model.AnimalType;
import com.shelter.demo.repo.AnimalRepository;
import com.shelter.demo.model.Animal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AnimalConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnimalRepository repository) {
        return args -> {
             Animal animal = new Animal(
                    123L, AnimalType.DOG, "Teo","Goof boy", LocalDate.of(2020, Month.APRIL, 13), LocalDate.of(2019, Month.APRIL, 13)
            );

            Animal animal2 = new Animal(
                    AnimalType.DOG, "Demo","Goofy boy", LocalDate.of(2020, Month.MARCH, 14), LocalDate.of(2021, Month.MARCH, 14)
            );

            repository.saveAll(
                    List.of(animal, animal2)
            );
        };
    }
}
