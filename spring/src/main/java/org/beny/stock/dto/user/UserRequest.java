package org.beny.stock.dto.user;

import lombok.Data;
import org.beny.stock.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
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

    public User getUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

}
