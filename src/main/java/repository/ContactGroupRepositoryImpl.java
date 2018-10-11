package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.ContactGroup;
import main.java.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactGroupRepositoryImpl implements ContactGroupRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();

    @Override
    public List<ContactGroup> getAllContactGroups() throws SQLException {

        List<ContactGroup> contactGroupList = new ArrayList<>();
        ContactGroup contactGroup = null;

        String query = "SELECT * FROM group_types";

        ResultSet rs = null;

        try (Statement statement = connection.createStatement();) {

            rs = statement.executeQuery(query);

            while (rs.next()) {
                contactGroup = new ContactGroup(rs.getLong("id"), rs.getString("group_name"));

                contactGroupList.add(contactGroup);
            }
        }
        return contactGroupList;
    }

    @Override
    public ContactGroup getContactGroupById(Long id) throws SQLException {

        ContactGroup contactGroup = new ContactGroup();

        String query = "SELECT * FROM group_types WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {

                contactGroup = new ContactGroup(rs.getLong("id"), rs.getString("group_name"));
                rs.close();

            }
        }
        return contactGroup;
    }
}
