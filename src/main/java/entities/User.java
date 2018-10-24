package main.java.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Cloneable{

    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String password;
    private String imagePath;
    private boolean active;
    private Role role;
    private List<Address> addressList;
    private Date birthdate;
    private Company company;
    private List<Email> emailList;
    private List<Note> noteList;
    private List<Phone> phoneList;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String nickName, String password, String imagePath, boolean active, Role role, Date birthdate, Company company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.imagePath = imagePath;
        this.active = active;
        this.role = role;
        this.birthdate = birthdate;
        this.company = company;
    }

    public User(Long id, String firstName, String lastName, String nickName, String password, String imagePath, boolean active, Role role, List<Address> addressList, Date birthdate, Company company, List<Email> emailList, List<Note> noteList, List<Phone> phoneList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.imagePath = imagePath;
        this.active = active;
        this.role = role;
        this.addressList = addressList;
        this.birthdate = birthdate;
        this.company = company;
        this.emailList = emailList;
        this.noteList = noteList;
        this.phoneList = phoneList;
    }

    public User(Long id, String firstName, String lastName, String nickName, String password, String imagePath, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.imagePath = imagePath;
        this.active = active;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(imagePath, user.imagePath) &&
                Objects.equals(role, user.role) &&
                Objects.equals(addressList, user.addressList) &&
                Objects.equals(birthdate, user.birthdate) &&
                Objects.equals(company, user.company) &&
                Objects.equals(emailList, user.emailList) &&
                Objects.equals(noteList, user.noteList) &&
                Objects.equals(phoneList, user.phoneList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickName, password, imagePath, active, role, addressList, birthdate, company, emailList, noteList, phoneList);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            user = new User(this.getId(), this.getFirstName(), this.getLastName(), this.getNickName(), this.getPassword(), this.getImagePath(), this.isActive());
        }

        if (user.role != null) {
            user.setRole((Role) this.role.clone());
        }

        for (int i = 0; i < this.getAddressList().size(); i++) {
            user.addressList.add((Address) this.getAddressList().get(i).clone());
        }

        if (user.birthdate != null) {
            user.birthdate = (Date) this.birthdate.clone();
        }

        if (user.company != null) {
            user.company = (Company) this.company.clone();
        }

        for (int i = 0; i < this.getEmailList().size(); i++) {
            user.emailList.add((Email) this.getEmailList().get(i).clone());
        }

        for (int i = 0; i < this.getNoteList().size(); i++) {
            user.noteList.add((Note) this.getNoteList().get(i).clone());
        }

        for (int i = 0; i < this.getPhoneList().size(); i++) {
            user.phoneList.add((Phone) this.getPhoneList().get(i).clone());
        }

        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", active=" + active +
                ", role=" + role +
                ", addressList=" + addressList +
                ", birthdate=" + birthdate +
                ", company=" + company +
                ", emailList=" + emailList +
                ", noteList=" + noteList +
                ", phoneList=" + phoneList +
                '}';
    }
}
