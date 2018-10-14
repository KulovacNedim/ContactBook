package main.java.service;

import main.java.entities.Role;
import main.java.repository.RoleRepositoryImpl;

import java.sql.SQLException;

public class RoleService {

    RoleRepositoryImpl roleRepo = new RoleRepositoryImpl();

    public Role getRoleById(Long id) throws SQLException {
        return roleRepo.getRoleById(id);
    }
}
