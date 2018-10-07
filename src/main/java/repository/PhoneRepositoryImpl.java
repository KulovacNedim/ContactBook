package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Phone;
import main.java.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepositoryImpl implements PhoneRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();
    private TypeRepositoryImpl typeRepo = new TypeRepositoryImpl();

    @Override
    public List<Phone> getPhoneListForUser(User user) throws SQLException {

        List<Phone> phoneList = new ArrayList<>();

        String query = "SELECT * FROM phones WHERE user_id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, user.getId());

            rs = statement.executeQuery(query);

            while (rs.next()) {
                Phone phone = new Phone(rs.getLong("id"), rs.getString("phone_description"),
                        rs.getString("phone_number"), typeRepo.getTypeById(rs.getLong("type")));
                phoneList.add(phone);
            }
        }

        return phoneList;
    }

    @Override
    public void updatePhoneListForUser(User user) throws SQLException {

        for (int i = 0; i < user.getPhoneList().size(); i++) {

            Phone phone = user.getPhoneList().get(i);

            if (phone.getId() != 0) {
                updatePhone(phone);
            } else {
                savePhone(phone, user.getId());
            }
        }
    }

    @Override
    public void updatePhone(Phone phone) throws SQLException {

        String query = "UPDATE phones SET phone_description = ?, phone_number = ?, type = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, phone.getPhoneDescription());
            statement.setString(2, phone.getPhoneNumber());
            statement.setLong(3, phone.getType().getId());
            statement.setLong(4, phone.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void savePhone(Phone phone, Long userId) throws SQLException {

        String query = "INSERT INTO phones(user_id, phone_description, phone_number, type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, userId);
            statement.setString(2, phone.getPhoneDescription());
            statement.setString(3, phone.getPhoneNumber());
            statement.setLong(4, phone.getType().getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deletePhoneListForUser(List<Phone> phoneList) throws SQLException {

        if (phoneList != null) {

            for (int i = 0; i < phoneList.size(); i++) {

                Phone phone = phoneList.get(i);

                String query = "DELETE FROM phones WHERE id = ?";

                try (PreparedStatement statement = connection.prepareStatement(query);) {

                    statement.setLong(1, phone.getId());

                    statement.executeUpdate();
                }
            }
        }
    }
}
