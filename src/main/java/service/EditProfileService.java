package main.java.service;

import main.java.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class EditProfileService {

    public void processRequest(HttpServletRequest req) throws CloneNotSupportedException, SQLException, IOException, ServletException {

        String flag = req.getParameter("flag") == null ? "refreshContact" : req.getParameter("flag");

        CompanyService companyService = new CompanyService();
        RoleService roleService = new RoleService();
        TypeService typeService = new TypeService();
        UserService userService = new UserService();
        ImageUploadService imageUploadService = new ImageUploadService();

        User contactToEdit = null;

        if (flag.equals("refreshContact")) {
            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");

        } else if (flag.equals("editRegContact")) {
            // editing registered contact
            contactToEdit = (User)((User) req.getSession().getAttribute("loggedInUser")).clone();
            User contactToEditTemp = userService.getUserByNickname(contactToEdit.getNickName());

            req.getSession().setAttribute("contactToEdit", contactToEditTemp);

        } else if (flag.equals("changeImage")) {
            // upload profile image
            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");
            contactToEdit.setImagePath(imageUploadService.uploadUserprofileImage(req));
            userService.updateUser(contactToEdit);
            req.getSession().setAttribute("contactToEdit", contactToEdit);
        }

        try {
            req.setAttribute("companies", companyService.getAllCompanies());
            req.setAttribute("roles", roleService.getAllRoles());
            req.setAttribute("types", typeService.getAllTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
