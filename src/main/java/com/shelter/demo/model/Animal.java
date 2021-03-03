package com.shelter.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity // for hibernate
@Table
public class Animal implements Serializable {

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
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;
    private String name;
    @Transient // no age column in db
    private Integer age;
    private String descr;
    private LocalDate dob;
    private LocalDate inShelterFrom;
    // TODO divide dob into day/month/year , add chip field

    public Animal(){}

    public Animal(Long id) {
        this.id = id;
    }

    public Animal(Long id, AnimalType animalType, String name, String descr, LocalDate inShelterFrom, LocalDate dob) {
        this.id = id;
        this.animalType = animalType;
        this.name = name;
        this.dob = dob;
        this.descr = descr;
        this.inShelterFrom = inShelterFrom;
    }

    public Animal(AnimalType animalType, String name, String descr, LocalDate inShelterFrom, LocalDate dob) {
        this.animalType = animalType;
        this.name = name;
        this.descr = descr;
        this.inShelterFrom = inShelterFrom;
        this.dob = dob;
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
        return Period.between(this.dob, LocalDate.now()).getYears(); // TODO if < 1 year retuern months
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animalType=" + animalType +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", descr='" + descr + '\'' +
                ", dob=" + dob +
                ", inShelterFrom=" + inShelterFrom +
                '}';
    }
}

