package main.java.service;

import main.java.entities.User;
import main.java.validation.PasswordHash;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegisterService {

    RoleService roleService = new RoleService();
    UserService userService = new UserService();
    PasswordHash hash = new PasswordHash();

    public void registerUser(String newNickname, String password, HttpServletRequest request) {

        User user = new User();

        user.setNickName(newNickname);
        user.setPassword(hash.getHash(password));
        user.setActive(true);
        user.setImagePath("defaultAvatar");
        try {
            user.setRole(roleService.getRoleById((long) 2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            userService.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String regSucc = "You are registered. Please edit your profile.";
        request.setAttribute("regSucc", regSucc);

        request.getSession().setAttribute("loggedInUser", user);
    }
}
