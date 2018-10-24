package main.java.entities;

import java.util.Date;
import java.util.Objects;

public class Note implements Cloneable {

    private Long id;
    private String noteDescription;
    private String note;
    private Date date;
    private boolean archived;

    public Note() {
    }

    public Note(Long id, String noteDescription, String note, Date date, boolean archived) {
        this.id = id;
        this.noteDescription = noteDescription;
        this.note = note;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                Objects.equals(note, note1.note) &&
                Objects.equals(date, note1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noteDescription, note, date, archived);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Note note = null;
        try {
            note = (Note) super.clone();
        } catch (CloneNotSupportedException e) {
            note = new Note(this.getId(), this.getNoteDescription(), this.getNote(), this.getDate(), this.isArchived());
        }
        note.date = (Date) this.date.clone();
        return note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteDescription='" + noteDescription + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", archived=" + archived +
                '}';
    }
}
