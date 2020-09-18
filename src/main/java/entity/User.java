package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static entity.OrderStatus.accepted;
import static entity.OrderStatus.completed;

public class User implements Serializable {
    private static final long serialVersionUID = 1;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String imgPath;

    public User() {

    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    private boolean emailSend;

    public User(String firstName, String secondName, String email, String password, boolean emailSend) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.emailSend = emailSend;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public boolean isEmailSend() {
        return emailSend;
    }

    public void setEmailSend(boolean emailSend) {
        this.emailSend = emailSend;
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


    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, email, password, imgPath, emailSend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User other = (User) o;
        return this.getEmail().equals(other.getEmail());
    }

    @Override
    public String toString() {
        return "entity.User{" +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", emailSend=" + emailSend +
                '}';
    }
}

