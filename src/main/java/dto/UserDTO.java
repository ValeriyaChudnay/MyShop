package dto;

import entity.Cart;
import entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1;
    private String firstName;
    private String secondName;
    private String email;
    private String imgPath;


    public UserDTO(User user) {
        firstName = user.getFirstName();
        secondName = user.getSecondName();
        email = user.getEmail();
        imgPath = user.getImgPath();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
