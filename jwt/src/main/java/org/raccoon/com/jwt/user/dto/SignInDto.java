package org.raccoon.com.jwt.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInDto {
    private String id;
    private String password;
}