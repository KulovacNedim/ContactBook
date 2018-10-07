package main.java.repository;

import main.java.entities.Phone;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface PhoneRepository {

    public List<Phone> getPhoneListForUser(User user) throws SQLException;

    public void updatePhoneListForUser(User user) throws SQLException;

    public void updatePhone(Phone phone) throws SQLException;

    public void savePhone(Phone phone, Long userId) throws SQLException;

    public void deletePhoneListForUser(List<Phone> phoneList) throws SQLException;
}
