package com.shelter.demo.model;

import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import javax.persistence.*;

@Entity
@Table
public class Image {

    @Id
    private String location;    // PK
    @ManyToOne(cascade=CascadeType.REMOVE)
    private Animal animal;    // FK
    private Boolean isMain = false;
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "img_sequence",
            sequenceName = "img_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "img_sequence"
    )
    private Integer seq;

    @Transient
    private byte[] img;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }


}
