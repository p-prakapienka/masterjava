package ru.javaops.masterjava.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String fullName;
    private String email;
    private UserFlag flag;

    public User() {}

    public User(String fullName, String email, UserFlag flag) {
        this(null, fullName, email, flag);
    }

    public User(Integer id, String fullName, String email, UserFlag flag) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserFlag getFlag() {
        return flag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFlag(UserFlag flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email) &&
                flag == user.flag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, flag);
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", flag=" + flag +
                ')';
    }
}