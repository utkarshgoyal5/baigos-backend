package com.utkarsh.baigos.services.impl;

import com.utkarsh.baigos.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File Name
        String name = file.getOriginalFilename();

        //Creating random name
        String randomId = UUID.randomUUID().toString();
        String newFileName = randomId + name.substring(name.lastIndexOf("."));

        //Full Path
        String filePath = path + File.separator + newFileName;

        //Create folder if not exist
        File newFile = new File(path);

        if(!newFile.exists()) {
            newFile.mkdir();
        }

        //Copy File
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
