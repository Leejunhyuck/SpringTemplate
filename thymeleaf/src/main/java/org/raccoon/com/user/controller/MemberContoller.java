package org.raccoon.com.user.controller;

import javax.transaction.Transactional;

import org.raccoon.com.user.domain.Member;
import org.raccoon.com.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member/")
public class MemberContoller {
    @Autowired
    PasswordEncoder pwEncoder;
    
    @Autowired
    MemberRepository repo;
  
    @GetMapping("/join")
    public void join(){
      
    }
  
  /*  @PostMapping("/join")
    public String joinPost(@ModelAttribute("member")Member member){
    
      log.info("MEMBER: " + member);
      
      return "/member/joinResult";
    }*/
    
    @Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member) {
  
      log.info("MEMBER: " + member);
      
      String encryptPw = pwEncoder.encode(member.getPassword());
      
      log.info("en: " + encryptPw);
  
      member.setPassword(encryptPw);
  
      repo.save(member);
  
      return "/member/joinResult";
    }
  

}