package main.java.service;

import main.java.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class EditProfileService {

    public void processRequest(HttpServletRequest req) throws SQLException, IOException, ServletException, CloneNotSupportedException {

        String flag = req.getParameter("flag") == null ? "refreshContact" : req.getParameter("flag");

        final String PATH = "C:\\Users\\User\\IdeaProjects\\ContactBook\\web\\images\\users";

        CompanyService companyService = new CompanyService();
        RoleService roleService = new RoleService();
        TypeService typeService = new TypeService();
        UserService userService = new UserService();

        User contactToEdit = null;

        if (flag.equals("refreshContact")) {
            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");

        } else if (flag.equals("editRegContact")) {
            // editing registered contact
            contactToEdit = (User)((User) req.getSession().getAttribute("loggedInUser")).clone();
            User contactToEditTemp = userService.getUserByNickname(contactToEdit.getNickName());

            req.getSession().setAttribute("contactToEdit", contactToEditTemp);

        } else if (flag.equals("changeImage")) {
            // uploading profile image
            Part filePart = req.getPart("file");
            String fileName = (Paths.get(filePart.getSubmittedFileName()).getFileName().toString()).substring
                    (0, (Paths.get(filePart.getSubmittedFileName()).getFileName().toString()).lastIndexOf('.'));

            File uploads = new File(PATH);

            File file = File.createTempFile(fileName, ".jpg", uploads);

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");
            contactToEdit.setImagePath(String.valueOf(Paths.get(file.getName())));
            userService.updateUser(contactToEdit);
            req.getSession().setAttribute("contactToEdit", contactToEdit);
        }

        try {
            req.setAttribute("companies", companyService.getAllCompanies());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            req.setAttribute("roles", roleService.getAllRoles());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            req.setAttribute("types", typeService.getAllTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
