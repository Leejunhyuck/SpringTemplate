package org.raccoon.com.user.repository;

import org.raccoon.com.user.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
