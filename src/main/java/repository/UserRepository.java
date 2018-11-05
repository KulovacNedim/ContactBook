package main.java.repository;

import main.java.entities.ContactGroup;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    public void addUser(User user) throws SQLException;

    public void updateUser(User user) throws SQLException;

    public void updatePassword(String password, User user) throws SQLException;

    public void deleteUser(User user) throws SQLException;

    public User getUserById(Long id) throws SQLException;

    public User getUserByNickname(String nickname) throws SQLException;

    public User getUserByNicknameExcludingMe(String nickname, User user) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public List<User> getAllUsersByActivity(boolean activity) throws SQLException;

    public List<User> getMyContactGroupList(User activeUser, ContactGroup contactGroup, boolean active) throws SQLException;

    public List<User> getUserListMatchingSearch(String search, boolean active) throws SQLException;
}
