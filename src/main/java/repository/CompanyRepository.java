package main.java.repository;

import main.java.entities.Company;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    public Company getCompanyById(Long id) throws SQLException;

    public List<Company> getAllCompanies() throws SQLException;

    public void updateCompanyForUser(User user) throws SQLException;
}
