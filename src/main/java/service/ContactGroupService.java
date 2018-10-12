package main.java.service;

import main.java.entities.ContactGroup;
import main.java.entities.User;
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

    public ContactGroup getContactGroupByUser(User loggedInUser, User refUser) throws SQLException {
        return contactGroupRepo.getContactGroupByUser(loggedInUser, refUser);
    }

    public void setContactGroupForUser(User loggedInUser, User refUser, Long contactGroupId) throws SQLException {
        contactGroupRepo.setContactGroupForUser(loggedInUser, refUser, contactGroupId);
    }
}
