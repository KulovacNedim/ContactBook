package main.java.service;

import main.java.entities.Company;
import main.java.repository.CompanyRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {

    CompanyRepositoryImpl companyRepo = new CompanyRepositoryImpl();

    public List<Company> getAllCompanies() throws SQLException {
        return companyRepo.getAllCompanies();
    }

    public Company getCompanyById(Long id) throws SQLException {
        return companyRepo.getCompanyById(id);
    }
}
