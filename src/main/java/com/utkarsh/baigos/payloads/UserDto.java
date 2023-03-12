package com.utkarsh.baigos.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "username must have at least 4 character")
    private String name;

    @Email(message = "Email address is not valid")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 18, message = "password must be of min 6 or max 18 characters long")
    private String password;

    @NotEmpty
    private String about;

}
