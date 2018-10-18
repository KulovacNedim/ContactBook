package main.java.repository;

import main.java.entities.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeRepository {

    public Type getTypeById(Long id) throws SQLException;

    public List<Type> getAllTypes() throws SQLException;
}
