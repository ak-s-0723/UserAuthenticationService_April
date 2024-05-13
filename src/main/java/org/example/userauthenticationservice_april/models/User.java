package org.example.userauthenticationservice_april.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class User extends BaseModel {
    private String email;

    private String password;

    @ManyToMany
    private Set<Role> roleSet = new HashSet<>();
}

//user : role
//1      m
//m       1