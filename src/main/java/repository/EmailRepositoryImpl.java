package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Email;
import main.java.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailRepositoryImpl implements EmailRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();
    private TypeRepositoryImpl typeRepo = new TypeRepositoryImpl();

    @Override
    public List<Email> getEmailListForUser(User user) throws SQLException {

        List<Email> emailList = new ArrayList<>();

        String query = "SELECT * FROM emails WHERE user_id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, user.getId());

            rs = statement.executeQuery();

            while (rs.next()) {
                Email email = new Email(rs.getLong("id"), rs.getString("email_description"),
                        rs.getString("email"), typeRepo.getTypeById(rs.getLong("type")));
                emailList.add(email);
            }
        }

        return emailList;
    }

    @Override
    public void updateEmailListForUser(User user) throws SQLException {

        for (int i = 0; i < user.getEmailList().size(); i++) {

            Email email = user.getEmailList().get(i);

            if (email.getId() != 0) {
                updateEmail(email);
            } else {
                saveEmail(email, user.getId());
            }
        }
    }

    @Override
    public void updateEmail(Email email) throws SQLException {

        String query = "UPDATE emails SET email_description = ?, email = ?, type = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, email.getEmailDescription());
            statement.setString(2, email.getEmail());
            statement.setLong(3, email.getType().getId());
            statement.setLong(4, email.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void saveEmail(Email email, Long userId) throws SQLException {

        String query = "INSERT INTO email(user_id, email_description, email, type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, userId);
            statement.setString(2, email.getEmailDescription());
            statement.setString(3, email.getEmail());
            statement.setLong(4, email.getType().getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteEmailListForUser(List<Email> emailList) throws SQLException {

        if (emailList != null) {

            for (int i = 0; i < emailList.size(); i++) {

                Email email = emailList.get(i);

                String query = "DELETE FROM emails WHERE id = ?";

                try (PreparedStatement statement = connection.prepareStatement(query);) {

                    statement.setLong(1, email.getId());

                    statement.executeUpdate();
                }
            }
        }
    }
}
