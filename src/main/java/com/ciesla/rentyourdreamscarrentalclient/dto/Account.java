package com.ciesla.rentyourdreamscarrentalclient.dto;

import com.ciesla.rentyourdreamscarrentalclient.validation.EmailValidator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Account {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 35, message = "First name must have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 35, message = "Last name must have at least 2 characters")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
//    @EmailValidator
    private String email;

    @NotNull
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    private Integer rentedCarId;

    public Account() {
    }

    public Account(Integer id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
