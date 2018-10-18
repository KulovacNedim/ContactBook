package main.java.service;

import main.java.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditProfileService {

    public void setParameters(Long id, String flag, HttpServletRequest req) {

        CompanyService companyService = new CompanyService();
        RoleService roleService = new RoleService();
        TypeService typeService = new TypeService();

        User contactToEdit = null;

        if (id != null) {
            // edit selected contact
        } else {
            if (flag.equals("addNewContact")) {
                // adding new contact
            } else if (flag.equals("editRegisteredContact")) {
                // editing registered contact
                contactToEdit = (User) req.getSession().getAttribute("loggedInUser");
            }
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

        req.setAttribute("contactToEdit", contactToEdit);
    }
}
