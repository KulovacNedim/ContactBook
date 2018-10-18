package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Company;
import main.java.entities.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Type> getAllTypes() throws SQLException {

        List<Type> types = new ArrayList<>();
        Type type = null;

        String query = "SELECT * FROM types";

        ResultSet rs = null;

        try (Statement statement = connection.createStatement();) {

            rs = statement.executeQuery(query);

            while (rs.next()) {
                types.add(new Type(rs.getLong("id"), rs.getString("type_name")));
            }
        }
        return types;
    }
}
