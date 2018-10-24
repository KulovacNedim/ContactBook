package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Company;
import main.java.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Company> getAllCompanies() throws SQLException {

        List<Company> companies = new ArrayList<>();
        Company company = null;

        String query = "SELECT * FROM companies";

        ResultSet rs = null;

        try (Statement statement = connection.createStatement();) {

            rs = statement.executeQuery(query);

            while (rs.next()) {
                companies.add(new Company(rs.getLong("id"), rs.getString("company")));
            }
        }
        return companies;
    }

    @Override
    public void updateCompanyForUser(User user) throws SQLException {

        Company company = null;

        String query = "SELECT * FROM companies WHERE id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, user.getId());

            rs = statement.executeQuery();

            if (rs.next()) {

                company = new Company(rs.getLong("id"), rs.getString("company"));
                rs.close();

                user.setCompany(company);
            }
        }
    }
}
