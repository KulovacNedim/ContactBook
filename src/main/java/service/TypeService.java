package main.java.service;

import main.java.entities.Type;
import main.java.repository.TypeRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class TypeService {

    TypeRepositoryImpl typeRepo = new TypeRepositoryImpl();

    public List<Type> getAllTypes() throws SQLException {
        return typeRepo.getAllTypes();
    }
}
