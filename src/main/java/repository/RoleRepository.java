package main.java.repository;

import main.java.entities.Role;

import java.sql.SQLException;

public interface RoleRepository {

    public Role getRoleById(Long id) throws SQLException;
}
