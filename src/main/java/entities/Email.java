package main.java.entities;

import java.util.Objects;

public class Email {

    private Long id;
    private String emailDescription;
    private String email;
    private Type type;

    public Email() {
    }

    public Email(Long id, String emailDescription, String email, Type type) {
        this.id = id;
        this.emailDescription = emailDescription;
        this.email = email;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailDescription() {
        return emailDescription;
    }

    public void setEmailDescription(String emailDescription) {
        this.emailDescription = emailDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(id, email1.id) &&
                Objects.equals(emailDescription, email1.emailDescription) &&
                Objects.equals(email, email1.email) &&
                Objects.equals(type, email1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailDescription, email, type);
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emailDescription='" + emailDescription + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
