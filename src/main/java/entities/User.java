package main.java.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String password;
    private Role role;
    private List<Address> addressList;
    private Date birthdate;
    private Company company;
    private List<Email> emailList;
    private List<Note> noteList;
    private List<Phone> phoneList;
    private List<ContactGroup> contactGroupList;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String nickName, String password, Role role, List<Address> addressList, Date birthdate, Company company, List<Email> emailList, List<Note> noteList, List<Phone> phoneList, List<ContactGroup> contactGroupList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = role;
        this.addressList = addressList;
        this.birthdate = birthdate;
        this.company = company;
        this.emailList = emailList;
        this.noteList = noteList;
        this.phoneList = phoneList;
        this.contactGroupList = contactGroupList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public List<ContactGroup> getContactGroupList() {
        return contactGroupList;
    }

    public void setContactGroupList(List<ContactGroup> contactGroupList) {
        this.contactGroupList = contactGroupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(addressList, user.addressList) &&
                Objects.equals(birthdate, user.birthdate) &&
                Objects.equals(company, user.company) &&
                Objects.equals(emailList, user.emailList) &&
                Objects.equals(noteList, user.noteList) &&
                Objects.equals(phoneList, user.phoneList) &&
                Objects.equals(contactGroupList, user.contactGroupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickName, password, role, addressList, birthdate, company, emailList, noteList, phoneList, contactGroupList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", addressList=" + addressList +
                ", birthdate=" + birthdate +
                ", company=" + company +
                ", emailList=" + emailList +
                ", noteList=" + noteList +
                ", phoneList=" + phoneList +
                ", contactGroupList=" + contactGroupList +
                '}';
    }
}
