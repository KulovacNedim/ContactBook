package main.java.repository;

import main.java.entities.Email;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface EmailRepository {

    public List<Email> getEmailListForUser(User user) throws SQLException;

    public void updateEmailListForUser(User user) throws SQLException;

    public void updateEmail(Email email) throws SQLException;

    public void saveEmail(Email email, Long userId) throws SQLException;

    public void deleteEmailListForUser(List<Email> emailList) throws SQLException;
}
