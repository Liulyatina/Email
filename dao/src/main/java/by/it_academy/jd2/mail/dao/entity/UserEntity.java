package by.it_academy.jd2.mail.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "users", schema = "mail_app")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private LocalDateTime birthday;
    @Column(name ="fullname")
    private String fullName;

    public UserEntity() {
    }

    public UserEntity(String email, String password, LocalDateTime birthday, String fullName) {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }
}
