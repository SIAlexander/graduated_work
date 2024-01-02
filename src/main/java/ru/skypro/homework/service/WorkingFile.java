package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface WorkingFile {
    Path getFilePath(String dir, MultipartFile file) throws IOException;
}
