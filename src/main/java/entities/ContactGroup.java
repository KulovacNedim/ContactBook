package main.java.entities;

import java.util.Objects;

public class ContactGroup {

    private Long id;
    private String contactGroup;

    public ContactGroup() {
    }

    public ContactGroup(Long id, String contactGroup) {
        this.id = id;
        this.contactGroup = contactGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactGroup() {
        return contactGroup;
    }

    public void setContactGroup(String contactGroup) {
        this.contactGroup = contactGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactGroup that = (ContactGroup) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contactGroup, that.contactGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactGroup);
    }

    @Override
    public String toString() {
        return "ContactGroup{" +
                "id=" + id +
                ", contactGroup='" + contactGroup + '\'' +
                '}';
    }
}
