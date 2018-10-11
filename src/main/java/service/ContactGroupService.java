package main.java.service;

import main.java.entities.ContactGroup;
import main.java.repository.ContactGroupRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ContactGroupService {

    private ContactGroupRepositoryImpl contactGroupRepo = new ContactGroupRepositoryImpl();

    public List<ContactGroup> getAllContactGroups() throws SQLException {
        return contactGroupRepo.getAllContactGroups();
    }

    public ContactGroup getContactGroupById(Long id) throws SQLException {
        return contactGroupRepo.getContactGroupById(id);
    }
}
