package org.raccoon.com.jwt.user.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "members")
@EqualsAndHashCode(of = "uid")
@ToString
public class Member {
  @Id
  private String uid;

  private String password;

  private String uname;

  @CreationTimestamp
  private Timestamp regdate;

  @UpdateTimestamp
  private Timestamp updatedate;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "uid")
  private List<MemberRole> roles;

}