package com.ua.convertr.serviceImpl;

import com.ua.convertr.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private final Path uploads = Paths.get("uploads");
    private final Path converted = Paths.get("converted");

    @Override
    public void save(MultipartFile multipartFile) {
        try {
            Files.copy(multipartFile.getInputStream(), this.uploads.resolve(multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource convert(String miltipartFileName) {
        try {
            Path path = uploads.resolve(miltipartFileName);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {

                File input = new File("uploads\\" + miltipartFileName);
                BufferedImage image = ImageIO.read(input);

                String newName = null;
                if (miltipartFileName.endsWith("bmp")) {
                    newName = miltipartFileName.replace("bmp", "jpg");
                }
                if (miltipartFileName.endsWith("BMP")) {
                    newName = miltipartFileName.replace("BMP", "jpg");
                }
                File output = new File("converted\\" + newName);
                ImageIO.write(image, "jpg", output);

                Path path1 = converted.resolve(newName);
                Resource resource1 = new UrlResource(path1.toUri());
                return resource1;

            } else {
                throw new Exception("Cannot read file");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
