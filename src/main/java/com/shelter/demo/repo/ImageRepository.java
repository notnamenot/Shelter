package com.shelter.demo.repo;

import com.shelter.demo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,String> {
    String RESOURCES_DIR = ImageRepository.class.getResource("/").getPath();

//    @Query("SELECT i from Image i WHERE i.animal = ?1")
//    Optional<List<Image>> findImagesByAnimal(Long animalId);

    @Query("SELECT coalesce(max(i.seq),0) from Image i WHERE i.animal = ?1")
    Integer getMaxSeq(Long animalId);
}


