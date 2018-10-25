package main.java.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/getImage")
public class ImageServingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String imageName = req.getParameter("name");

        final String PATH = "C:\\Users\\User\\IdeaProjects\\ContactBook\\web\\images\\users";

        File file = new File(PATH, imageName);
        resp.setHeader("Content-Type", getServletContext().getMimeType(imageName));
        resp.setHeader("Content-Length", String.valueOf(file.length()));
//        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), resp.getOutputStream());
    }
}
