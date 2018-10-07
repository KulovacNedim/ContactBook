package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Note;
import main.java.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteRepositoryImpl implements NoteRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();

    @Override
    public List<Note> getNoteListForUser(User user) throws SQLException {

        List<Note> noteList = new ArrayList<>();

        String query = "SELECT * FROM notes WHERE user_id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, user.getId());

            rs = statement.executeQuery(query);

            while (rs.next()) {
                Note note = new Note(rs.getLong("id"), rs.getString("note_description"),
                        rs.getString("note"), rs.getBoolean("archived"));
                noteList.add(note);
            }
        }

        return noteList;
    }

    @Override
    public void updateNoteListForUser(User user) throws SQLException {

        for (int i = 0; i < user.getNoteList().size(); i++) {

            Note note = user.getNoteList().get(i);

            if (note.getId() != 0) {
                updateNote(note);
            } else {
                saveNote(note, user.getId());
            }
        }
    }

    @Override
    public void updateNote(Note note) throws SQLException {

        String query = "UPDATE notes SET note_description = ?, note = ?, archived = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, note.getNoteDescription());
            statement.setString(2, note.getNote());
            statement.setBoolean(3, note.isArchived());
            statement.setLong(4, note.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void saveNote(Note note, Long userId) throws SQLException {

        String query = "INSERT INTO notes(user_id, note_description, note, archived) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, userId);
            statement.setString(2, note.getNoteDescription());
            statement.setString(3, note.getNote());
            statement.setBoolean(4, note.isArchived());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteNoteListForUser(List<Note> noteList) throws SQLException {

        if (noteList != null) {

            for (int i = 0; i < noteList.size(); i++) {

                Note note = noteList.get(i);

                String query = "DELETE FROM notes WHERE id = ?";

                try (PreparedStatement statement = connection.prepareStatement(query);) {

                    statement.setLong(1, note.getId());

                    statement.executeUpdate();
                }
            }
        }
    }
}
