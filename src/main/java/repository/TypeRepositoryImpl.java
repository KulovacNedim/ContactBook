package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeRepositoryImpl implements TypeRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();

    @Override
    public Type getTypeById(Long id) throws SQLException {

        Type type = null;

        String query = "SELECT * FROM types WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {

                type = new Type(rs.getLong("id"), rs.getString("type_name"));

                rs.close();
            }

            return type;
        }
    }
}
