package main.java.service;

import main.java.entities.User;
import main.java.validation.PasswordHash;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegisterService {

    private RoleService roleService = new RoleService();
    private UserService userService = new UserService();

    public void registerUser(String newNickname, String password, HttpServletRequest request) throws SQLException {

        User loggedInUser = new User();

        loggedInUser.setNickName(newNickname);
        loggedInUser.setPassword(PasswordHash.getHash(password));
        loggedInUser.setActive(true);
        loggedInUser.setImagePath("defaultAvatar.jpg");
        try {
            loggedInUser.setRole(roleService.getRoleById((long) 2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            userService.addUser(loggedInUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String regSucc = "You are registered. Please edit your profile.";
        request.setAttribute("regSucc", regSucc);

        loggedInUser = userService.getUserByNickname(loggedInUser.getNickName()); // fetch user id from db
        request.getSession().setAttribute("loggedInUser", loggedInUser);
    }
}
