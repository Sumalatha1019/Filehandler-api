package com.example.Filehandler.service;

import com.example.Filehandler.model.File;
import com.example.Filehandler.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

        public String uploadFile(MultipartFile file) throws IOException {
            File fileData = fileRepository.save(File.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .fileData(file.getBytes()).build());

        if (fileData != null) {
            return "File uploaded successfully:" + file.getOriginalFilename();
        }
        return null;
    }

    @Transactional
    public File downloadFile(String fileName){
        return fileRepository.findByName(fileName).orElse(null);
    }

    @Transactional
    public List<String> getFileList() {
        List<File> files = fileRepository.findAll(); // Adjust the method based on your entity and repository

        // Assuming FileEntity has a 'fileName' property
        return files.stream()
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public String deleteFile(String fileName) {
        Optional<File> fileData = fileRepository.findByName(fileName);
        if (fileData.isPresent()) {
            fileRepository.delete(fileData.get());
            return "File deleted successfully: " + fileName;
        }
        return "File not found: " + fileName;
    }

}

