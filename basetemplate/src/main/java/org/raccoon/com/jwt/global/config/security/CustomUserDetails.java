package org.raccoon.com.jwt.global.config.security;

import java.util.*;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

import org.raccoon.com.jwt.user.domain.Member;
import org.raccoon.com.jwt.user.domain.MemberRole;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class CustomUserDetails extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public CustomUserDetails(Member member) {

        super(member.getUid(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
        this.member = member;

    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {

        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

        return list;
    }

}