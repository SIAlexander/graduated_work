package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.WorkingFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.datical.liquibase.ext.init.InitProjectUtil.getExtension;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class WorkingFileImpl implements WorkingFile {

    /**
     * Метод получения ссылки на файл
     *
     * @param dir
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public Path getFilePath(String dir, MultipartFile file) throws IOException {
        Path filePath = Path.of(dir, file.getOriginalFilename() + "_"
                + file.getSize() + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 512);
             BufferedOutputStream bos = new BufferedOutputStream(os, 512);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }
}
