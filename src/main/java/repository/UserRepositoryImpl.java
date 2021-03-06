package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.ContactGroup;
import main.java.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();
    private RoleRepositoryImpl roleRepo = new RoleRepositoryImpl();
    private AddressRepositoryImpl addressRepo = new AddressRepositoryImpl();
    private CompanyRepositoryImpl companyRepo = new CompanyRepositoryImpl();
    private EmailRepositoryImpl emailRepo = new EmailRepositoryImpl();
    private NoteRepositoryImpl noteRepo = new NoteRepositoryImpl();
    private PhoneRepositoryImpl phoneRepo = new PhoneRepositoryImpl();

    @Override
    public void addUser(User user) throws SQLException {

        String query = "INSERT INTO users(first_name, last_name, nickname, password, image_path, active, role_id, birthdate, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getImagePath());
            statement.setBoolean(6, user.isActive());
            statement.setLong(7, user.getRole().getId());
            statement.setDate(8, (java.sql.Date) user.getBirthdate());
            statement.setLong(9, user.getCompany() == null ? 0 : user.getCompany().getId());

            statement.executeUpdate();
        }
        //I'm not saving addressList, phoneList, emailList, noteList. Those attributes will be set later
    }

    @Override
    public void updateUser(User user) throws SQLException {

        String query = "UPDATE users SET first_name = ?, last_name = ?, nickname = ?, password = ?, image_path = ?, " +
                "active = ?, role_id = ?, birthdate = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getImagePath());
            statement.setBoolean(6, user.isActive());
            statement.setLong(7, user.getRole().getId());
            statement.setDate(8, user.getBirthdate() == null ? null : new java.sql.Date((user.getBirthdate()).getTime()));
            statement.setLong(9, user.getId());

            statement.executeUpdate();

            if (user.getCompany() != null && user.getCompany().getId() != 0) {
                companyRepo.updateCompanyForUser(user);
            }
            addressRepo.updateAddressListForUser(user);
            emailRepo.updateEmailListForUser(user);
            noteRepo.updateNoteListForUser(user);
            phoneRepo.updatePhoneListForUser(user);
        }
    }

    @Override
    public void updatePassword(String password, User user) throws SQLException {

        String query = "UPDATE users SET password = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, password);
            statement.setLong(2, user.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {

        if (user != null) {

            addressRepo.deleteAddressListForUser(user.getAddressList());
            emailRepo.deleteEmailListForUser(user.getEmailList());
            noteRepo.deleteNoteListForUser(user.getNoteList());
            phoneRepo.deletePhoneListForUser(user.getPhoneList());


            String query = "DELETE FROM users WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query);) {

                statement.setLong(1, user.getId());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public User getUserById(Long id) throws SQLException {

        User user = new User();

        String query = "SELECT * FROM users WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {

                user = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));
                rs.close();

                setAttributesToUser(user);
            }
        }
        return user;
    }

    private void setAttributesToUser(User user) throws SQLException {
        user.setAddressList(addressRepo.getAddressListForUser(user));
        user.setEmailList(emailRepo.getEmailListForUser(user));
        user.setNoteList(noteRepo.getNoteListForUser(user));
        user.setPhoneList(phoneRepo.getPhoneListForUser(user));
    }

    @Override
    public User getUserByNickname(String nickname) throws SQLException {

        User user = new User();

        String query = "SELECT * FROM users WHERE nickname = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, nickname);

            rs = statement.executeQuery();

            if (rs.next()) {

                user = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));

                rs.close();
                setAttributesToUser(user);
            }
        }
        return user;
    }

    @Override
    public User getUserByNicknameExcludingMe(String nickname, User user) throws SQLException {

        User anotherUser = new User();

        String query = "SELECT * FROM users WHERE nickname = ? AND id <> ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, nickname);
            statement.setLong(2, user.getId());

            rs = statement.executeQuery();

            if (rs.next()) {

                anotherUser = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));

                rs.close();
                setAttributesToUser(anotherUser);
            }
        }
        return anotherUser;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();
        User user = null;

        String query = "SELECT * FROM users";

        ResultSet rs = null;

        try (Statement statement = connection.createStatement();) {

            rs = statement.executeQuery(query);

            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));

                setAttributesToUser(user);
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public List<User> getAllUsersByActivity(boolean activity) throws SQLException {

        List<User> userList = new ArrayList<>();
        User user = null;

        String query = "SELECT * FROM users WHERE active = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setBoolean(1, activity);

            rs = statement.executeQuery();

            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));

                setAttributesToUser(user);
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public List<User> getMyContactGroupList(User activeUser, ContactGroup contactGroup, boolean active) throws SQLException {

        List<User> refUserList = new ArrayList<>();
        User refUser = null;

        String query = "SELECT * FROM contact_groups WHERE active_user_id = ? AND group_type = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, activeUser.getId());
            statement.setLong(2, contactGroup.getId());

            rs = statement.executeQuery();

            while (rs.next()) {
                refUser = getUserById(rs.getLong("ref_user_id"));

                if (refUser.isActive() == active) {
                    setAttributesToUser(refUser);
                    refUserList.add(refUser);
                }
            }
        }
        return refUserList;
    }

    public List<User> getUserListMatchingSearch(String search, boolean active) throws SQLException {

        List<User> serchResult = new ArrayList<>();
        User found = null;

        String query = "SELECT * FROM users WHERE CONCAT(first_name, ' ', last_name) LIKE ? AND active = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, "%" + search + "%");
            statement.setBoolean(2, active);

            rs = statement.executeQuery();

            while (rs.next()) {
                found = getUserById(rs.getLong("id"));

                setAttributesToUser(found);
                serchResult.add(found);
            }
        }
        return serchResult;
    }
}
