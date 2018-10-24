package main.java.entities;

import java.util.Objects;

public class Phone implements Cloneable {

    private Long id;
    private String phoneDescription;
    private String phoneNumber;
    private Type type;

    public Phone() {
    }

    public Phone(Long id, String phoneDescription, String phoneNumber, Type type) {
        this.id = id;
        this.phoneDescription = phoneDescription;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneDescription() {
        return phoneDescription;
    }

    public void setPhoneDescription(String phoneDescription) {
        this.phoneDescription = phoneDescription;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(phoneDescription, phone.phoneDescription) &&
                Objects.equals(phoneNumber, phone.phoneNumber) &&
                Objects.equals(type, phone.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneDescription, phoneNumber, type);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Phone phone = null;
        try {
            phone = (Phone) super.clone();
        } catch (CloneNotSupportedException e) {
            phone = new Phone(this.getId(), this.getPhoneDescription(), this.getPhoneNumber(), this.getType());
        }
        phone.type = (Type) this.type.clone();
        return phone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneDescription='" + phoneDescription + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
