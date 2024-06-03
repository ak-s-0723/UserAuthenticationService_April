package org.example.userauthenticationservice_april.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice_april.models.Role;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserDto {
    private String email;

    //private Set<Role> roleSet = new HashSet<>();
}
