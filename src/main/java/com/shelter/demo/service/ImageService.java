package com.shelter.demo.service;

import com.shelter.demo.model.Image;
import com.shelter.demo.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@Service
public class ImageService {

    public final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(byte[] image, Long animalId) {


    }

    private String createFileName(Long animalId) {
        Integer nextSeq = imageRepository.getMaxSeq(animalId) + 1;

        return animalId + "_" + nextSeq + "_" + new Date().getTime();
    }
}
