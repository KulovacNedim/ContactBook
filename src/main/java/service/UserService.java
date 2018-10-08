package main.java.service;

import main.java.entities.User;
import main.java.repository.UserRepositoryImpl;

import java.sql.SQLException;

public class UserService {

    private UserRepositoryImpl userRepo = new UserRepositoryImpl();

    public User getUserByNickname(String nickname) throws SQLException {
        return userRepo.getUserByNickname(nickname);
    }
}
