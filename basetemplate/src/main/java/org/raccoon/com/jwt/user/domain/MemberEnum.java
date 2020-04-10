package org.raccoon.com.jwt.user.domain;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "uid")
@ToString
public class MemberEnum {
  @Id
  private String uid;

  private String password;

  private String uname;

  @CreationTimestamp
  private Timestamp regdate;

  @UpdateTimestamp
  private Timestamp updatedate;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Role> roles;

}