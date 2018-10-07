package main.java.repository;

import main.java.entities.Note;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface NoteRepository {

    public List<Note> getNoteListForUser(User user) throws SQLException;

    public void updateNoteListForUser(User user) throws SQLException;

    public void updateNote(Note note) throws SQLException;

    public void saveNote(Note note, Long userId) throws SQLException;

    public void deleteNoteListForUser(List<Note> noteList) throws SQLException;
}
