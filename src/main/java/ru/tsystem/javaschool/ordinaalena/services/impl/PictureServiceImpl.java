package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.services.api.PictureService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class PictureServiceImpl implements PictureService {
    private static final String PATH="..webapp/ROOT/img/product-img";
    private static final Logger logger = Logger.getLogger(PictureServiceImpl.class);
    @Override
    public void savePicture(int id, MultipartFile multipartFile) {
        boolean isCreated = false;
        File file = new File( PATH + id + ".jpg");
        try(FileOutputStream stream =  new FileOutputStream(file)) {
            isCreated = file.createNewFile();
            byte[] bytes = multipartFile.getBytes();

            stream.write(bytes);
            stream.flush();

        }catch (IOException ioexc){
            logger.error("file isn't writed. Is created: " + String.valueOf(isCreated) + ", id: " + id);
        }
    }
}
