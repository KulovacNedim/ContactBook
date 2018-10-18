package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Company;
import main.java.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();

    @Override
    public Role getRoleById(Long id) throws SQLException {

        Role role = null;

        String query = "SELECT * FROM roles WHERE id = ?";

        ResultSet rs = null;

            try (PreparedStatement statement = connection.prepareStatement(query);) {

                statement.setLong(1, id);

                rs = statement.executeQuery();

                if (rs.next()) {

                    role = new Role(rs.getLong("id"), rs.getString("role_name"));

                    rs.close();
                }

                return role;
        }
    }

    @Override
    public List<Role> getAllRoles() throws SQLException {

        List<Role> roles = new ArrayList<>();
        Role role = null;

        String query = "SELECT * FROM roles";

        ResultSet rs = null;

        try (Statement statement = connection.createStatement();) {

            rs = statement.executeQuery(query);

            while (rs.next()) {
                roles.add(new Role(rs.getLong("id"), rs.getString("role_name")));
            }
        }
        return roles;
    }
}

