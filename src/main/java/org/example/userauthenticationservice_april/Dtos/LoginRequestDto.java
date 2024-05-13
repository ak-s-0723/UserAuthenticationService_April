package org.example.userauthenticationservice_april.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String email;

    private String password;
}
