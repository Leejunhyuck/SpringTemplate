package org.raccoon.com.jwt.user.repository;

import org.raccoon.com.jwt.user.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
