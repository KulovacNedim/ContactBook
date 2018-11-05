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

    public User getUserByNicknameExcludingMe(String nickname, User user) throws SQLException {
        return userRepo.getUserByNicknameExcludingMe(nickname, user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepo.getAllUsers();
    }

    public List<User> getAllUsersByActivity(boolean activity) throws SQLException {
        return userRepo.getAllUsersByActivity(activity);
    }

    public User getUserById(Long id) throws SQLException {
        return userRepo.getUserById(id);
    }

    public List<User> getMyContactGroupList(User activeUser, ContactGroup contactGroup, boolean active) throws SQLException {
        return userRepo.getMyContactGroupList(activeUser, contactGroup, active);
    }

    public List<User> getUserListMatchingSearch(String search, boolean active) throws SQLException {
        return userRepo.getUserListMatchingSearch(search, active);
    }

    public void addUser(User user) throws SQLException {
        userRepo.addUser(user);
    }

    public void updateUser(User user) throws SQLException {
        userRepo.updateUser(user);
    }
}
