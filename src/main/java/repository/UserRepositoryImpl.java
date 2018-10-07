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
            statement.setDate(8, (Date) user.getBirthdate());
            statement.setLong(9, user.getCompany().getId());

            statement.executeUpdate();
        }
        //I'm not saving addressList, phoneList, emailList, noteList. Those attributes will be set later
    }

    @Override
    public void updateUser(User user) throws SQLException {

        String query = "UPDATE users SET first_name = ?, last_name = ?, nickname = ?, password = ?, image_path = ?, " +
                "active = ?, role_id = ?, birthdate = ?, company_id = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getImagePath());
            statement.setBoolean(6, user.isActive());
            statement.setLong(7, user.getRole().getId());
            statement.setDate(8, (Date) user.getBirthdate());
            statement.setLong(9, user.getCompany().getId());
            statement.setLong(10, user.getId());

            statement.executeUpdate();

            addressRepo.updateAddressListForUser(user);
            emailRepo.updateEmailListForUser(user);
            noteRepo.updateNoteListForUser(user);
            phoneRepo.updatePhoneListForUser(user);
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
    public List<User> getMyContactGroupList(User activeUser, ContactGroup contactGroup) throws SQLException {

        List<User> refUserList = new ArrayList<>();
        User refUser = null;

        String query = "SELECT * FROM contact_groups WHERE active_user_id = ? AND group_type = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, activeUser.getId());
            statement.setLong(2, contactGroup.getId());

            rs = statement.executeQuery();

            while (rs.next()) {
                refUser = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("nickname"), rs.getString("password"), rs.getString("image_path"),
                        rs.getBoolean("active"), roleRepo.getRoleById(rs.getLong("role_id")),
                        rs.getDate("birthdate"), companyRepo.getCompanyById(rs.getLong("company_id")));

                setAttributesToUser(refUser);
                refUserList.add(refUser);
            }
        }
        return refUserList;
    }
}
