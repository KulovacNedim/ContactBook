package main.java.repository;

import main.java.entities.Type;

import java.sql.SQLException;

public interface TypeRepository {

    public Type getTypeById(Long id) throws SQLException;
}
