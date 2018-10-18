package main.java.service;

import main.java.entities.Role;
import main.java.repository.RoleRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class RoleService {

    RoleRepositoryImpl roleRepo = new RoleRepositoryImpl();

    public Role getRoleById(Long id) throws SQLException {
        return roleRepo.getRoleById(id);
    }

    public List<Role> getAllRoles() throws SQLException {
        return roleRepo.getAllRoles();
    }
}
