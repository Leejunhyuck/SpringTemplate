package org.raccoon.com.jwt.user.dto;

import java.util.List;

import org.raccoon.com.jwt.user.domain.MemberRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDto {
    private String id;
    private String password;
    private String name;
    private List<MemberRole> roles;
}