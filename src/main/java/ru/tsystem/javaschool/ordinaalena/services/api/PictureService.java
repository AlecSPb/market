package ru.tsystem.javaschool.ordinaalena.services.api;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    public void savePicture(int id, MultipartFile multipartFile);
}
