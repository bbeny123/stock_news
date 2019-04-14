package org.beny.stock.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ResendRequest {

    @NotEmpty
    @Email
    @Length(max = 60)
    private String email;

}
