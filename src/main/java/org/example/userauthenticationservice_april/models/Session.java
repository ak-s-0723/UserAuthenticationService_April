package org.example.userauthenticationservice_april.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice_april.services.AuthService;
import org.springframework.stereotype.Service;

@Entity
@Setter
@Getter
public class Session extends BaseModel{
    private String token;

    @Enumerated(EnumType.ORDINAL)
    private SessionState sessionState;

    @ManyToOne
    private User user;
}
