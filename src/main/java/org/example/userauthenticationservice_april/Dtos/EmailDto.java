package org.example.userauthenticationservice_april.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailDto {
    private String to;

    private String from;

    private String subject;

    private String body;
}
