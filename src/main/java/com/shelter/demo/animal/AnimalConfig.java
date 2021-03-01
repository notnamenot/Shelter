package com.shelter.demo.animal;

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
                    123L, AnimalType.DOG, "Teo",1,"Goof boy", LocalDate.of(2020, Month.APRIL, 13)
            );

            Animal animal2 = new Animal(
                    AnimalType.DOG, "Demo",2,"Goofy boy", LocalDate.of(2021, Month.MARCH, 14)
            );

            repository.saveAll(
                    List.of(animal, animal2)
            );
        };
    }
}
