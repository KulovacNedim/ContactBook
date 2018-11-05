package main.java.service;

import main.java.entities.Company;
import main.java.entities.Role;
import main.java.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            // edit registered contact
            contactToEdit = (User)((User) req.getSession().getAttribute("loggedInUser")).clone();
            User contactToEditTemp = userService.getUserByNickname(contactToEdit.getNickName());

            req.getSession().setAttribute("contactToEdit", contactToEditTemp);

        } else if (flag.equals("changeImage")) {
            // upload profile image
            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");
            contactToEdit.setImagePath(imageUploadService.uploadUserprofileImage(req));
            userService.updateUser(contactToEdit);
            req.getSession().setAttribute("contactToEdit", contactToEdit);

        } else if (flag.equals("updateGenInfo")) {
            // edit general info

            contactToEdit = (User) req.getSession().getAttribute("contactToEdit");

            contactToEdit.setFirstName(req.getParameter("first_name"));
            contactToEdit.setLastName(req.getParameter("last_name"));

            String newNickname = req.getParameter("nick_name");
            if ((userService.getUserByNicknameExcludingMe(newNickname, contactToEdit)).getId() == null) {
                contactToEdit.setNickName(newNickname);
            } else  {
                String messageNicknameExist = "User with nickname " + (newNickname) + " already exist. " +
                        "Other informations are saved.";
                req.setAttribute("messageNicknameExist", messageNicknameExist);
            }

            if (Long.valueOf(req.getParameter("company")) != 0) {
                Company company = companyService.getCompanyById(Long.valueOf(req.getParameter("company")));
                contactToEdit.setCompany(company);
            }

            String dateStr = req.getParameter("birthdate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(dateStr);
                contactToEdit.setBirthdate(date);

                String formattedDateString = String.valueOf((new SimpleDateFormat("yyyy-MM-dd")).format(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr)));
                req.setAttribute("DOB", formattedDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Role role = roleService.getRoleById(Long.valueOf(req.getParameter("role")));
            contactToEdit.setRole(role);

            boolean active = req.getParameter("activity").equals("active") ? true : false;
            contactToEdit.setActive(active);

            userService.updateUser(contactToEdit);
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
