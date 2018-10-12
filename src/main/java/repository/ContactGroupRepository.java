package main.java.repository;

import main.java.entities.ContactGroup;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface ContactGroupRepository {

    public List<ContactGroup> getAllContactGroups() throws SQLException;

    public ContactGroup getContactGroupById(Long id) throws SQLException;

    public ContactGroup getContactGroupByUser(User loggedInUser, User refUser) throws SQLException;

    public void setContactGroupForUser(User loggedInUser, User refUser, Long contactGroupId) throws SQLException;
}
