package main.java.service;

import main.java.entities.ContactGroup;
import main.java.entities.User;
import main.java.repository.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserRepositoryImpl userRepo = new UserRepositoryImpl();

    public User getUserByNickname(String nickname) throws SQLException {
        return userRepo.getUserByNickname(nickname);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepo.getAllUsers();
    }

    public User getUserById(Long id) throws SQLException {
        return userRepo.getUserById(id);
    }

    public List<User> getMyContactGroupList(User activeUser, ContactGroup contactGroup) throws SQLException {
        return userRepo.getMyContactGroupList(activeUser, contactGroup);
    }

    public List<User> getUserListMatchingSearch(String search) throws SQLException {
        return userRepo.getUserListMatchingSearch(search);
    }
}
