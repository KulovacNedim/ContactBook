package main.java.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageUploadService {

    public String uploadUserprofileImage(HttpServletRequest req) throws IOException, ServletException {

        final String PATH = "C:\\Users\\User\\IdeaProjects\\ContactBook\\web\\images\\users";

        Part filePart = req.getPart("file");
        String fileName = (Paths.get(filePart.getSubmittedFileName()).getFileName().toString()).substring
                (0, (Paths.get(filePart.getSubmittedFileName()).getFileName().toString()).lastIndexOf('.'));

        File uploads = new File(PATH);

        File file = File.createTempFile(fileName, ".jpg", uploads);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return String.valueOf(Paths.get(file.getName()));
    }
}
