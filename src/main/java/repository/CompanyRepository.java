package main.java.repository;

import main.java.entities.Company;

import java.sql.SQLException;

public interface CompanyRepository {

    public Company getCompanyById(Long id) throws SQLException;
}
