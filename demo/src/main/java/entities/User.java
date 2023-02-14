package entities;

import java.time.LocalDate;

public class User extends Person {
    private Long id;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private EducationLevel  educationLevel;
    private LocalDate dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User(Long id, Boolean isAdmin, String firstName, String lastName, String email, String userName, String password, EducationLevel educationLevel, LocalDate dateOfBirth) {
        super(id);
        this.id = id;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.educationLevel = educationLevel;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isAdmin=" + isAdmin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", educationLevel=" + educationLevel +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
