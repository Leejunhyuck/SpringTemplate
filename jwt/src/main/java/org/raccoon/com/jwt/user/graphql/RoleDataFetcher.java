package org.raccoon.com.jwt.user.graphql;

import org.raccoon.com.jwt.user.domain.Member;
import org.raccoon.com.jwt.user.repository.MemberRepository;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleDataFetcher implements DataFetcher<Member> {

    private final MemberRepository memberRepository;

    @Override
    public Member get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("uid");
        return memberRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("no such data"));
    }
}