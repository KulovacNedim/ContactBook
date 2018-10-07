package main.java.entities;

import java.util.Objects;

public class Note {

    private Long id;
    private String noteDescription;
    private String note;
    private boolean archived;

    public Note() {
    }

    public Note(Long id, String noteDescription, String note, boolean archived) {
        this.id = id;
        this.noteDescription = noteDescription;
        this.note = note;
        this.archived = archived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return archived == note1.archived &&
                Objects.equals(id, note1.id) &&
                Objects.equals(noteDescription, note1.noteDescription) &&
                Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noteDescription, note, archived);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteDescription='" + noteDescription + '\'' +
                ", note='" + note + '\'' +
                ", archived=" + archived +
                '}';
    }
}
