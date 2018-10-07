package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRepositoryImpl implements CompanyRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();

    @Override
    public Company getCompanyById(Long id) throws SQLException {

        Company company = null;

        String query = "SELECT * FROM companies WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {

                company = new Company(rs.getLong("id"), rs.getString("company"));

                rs.close();
            }

            return company;
        }
    }
}
