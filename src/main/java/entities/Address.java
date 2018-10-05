package main.java.entities;

import java.util.Objects;

public class Address {

    private Long id;
    private User user;
    private String addressDescription;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    private Type type;

    public Address() {
    }

    public Address(Long id, User user, String addressDescription, String state, String city, String street, String zipCode, Type type) {
        this.id = id;
        this.user = user;
        this.addressDescription = addressDescription;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(user, address.user) &&
                Objects.equals(addressDescription, address.addressDescription) &&
                Objects.equals(state, address.state) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(type, address.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, addressDescription, state, city, street, zipCode, type);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", user=" + user +
                ", addressDescription='" + addressDescription + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", type=" + type +
                '}';
    }
}
