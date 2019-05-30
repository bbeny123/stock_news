package org.beny.stock.dto.user;

import org.beny.stock.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRequest {

    @NotEmpty
    @Length(max = 30)
    private String login;

    @NotEmpty
    @Email
    @Length(max = 60)
    private String email;

    @NotEmpty
    @Length(max = 255)
    private String password;

    @Length(max = 120)
    private String name;

    private String captchaResponse;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }

    public User getUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

}
