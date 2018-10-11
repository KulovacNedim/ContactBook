package main.java.repository;

import main.java.entities.ContactGroup;

import java.sql.SQLException;
import java.util.List;

public interface ContactGroupRepository {

    public List<ContactGroup> getAllContactGroups() throws SQLException;

    public ContactGroup getContactGroupById(Long id) throws SQLException;
}
