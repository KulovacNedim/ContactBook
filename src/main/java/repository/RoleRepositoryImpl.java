package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

