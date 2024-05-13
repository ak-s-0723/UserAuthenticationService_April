package org.example.userauthenticationservice_april.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String email;

    private String password;
}
