package com.shelter.demo.animal;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // for hibernate
@Table
public class Animal {

    @Id
    @SequenceGenerator(
            name = "animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_sequence"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;
    private String name;
    private Integer age;
    private String descr;
    private LocalDate inShelterFrom;

    public Animal(){}

    public Animal(Long id) {
        this.id = id;
    }

    public Animal(Long id, AnimalType animalType, String name, Integer age, String descr, LocalDate inShelterFrom) {
        this.id = id;
        this.animalType = animalType;
        this.name = name;
        this.age = age;
        this.descr = descr;
        this.inShelterFrom = inShelterFrom;
    }

    public Animal(AnimalType animalType, String name, Integer age, String descr, LocalDate inShelterFrom) {
        this.animalType = animalType;
        this.name = name;
        this.age = age;
        this.descr = descr;
        this.inShelterFrom = inShelterFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public LocalDate getInShelterFrom() {
        return inShelterFrom;
    }

    public void setInShelterFrom(LocalDate inShelterFrom) {
        this.inShelterFrom = inShelterFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animalType=" + animalType +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", descr='" + descr + '\'' +
                ", inShelterFrom=" + inShelterFrom +
                '}';
    }
}

