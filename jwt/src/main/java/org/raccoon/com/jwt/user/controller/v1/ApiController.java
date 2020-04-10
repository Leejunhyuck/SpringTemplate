package org.raccoon.com.jwt.user.controller.v1;

import java.util.ArrayList;
import java.util.List;
import org.raccoon.com.jwt.global.config.jwt.JwtTokenProvider;
import org.raccoon.com.jwt.user.domain.Member;
import org.raccoon.com.jwt.user.dto.ReqDto;
import org.raccoon.com.jwt.user.dto.SignInDto;
import org.raccoon.com.jwt.user.dto.SignUpDto;
import org.raccoon.com.jwt.user.graphql.GraphUseCase;
import org.raccoon.com.jwt.user.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/*")
@RequiredArgsConstructor
@CrossOrigin
public class ApiController{

    private final MemberRepository memberRepository;
    private final PasswordEncoder pwEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwttokenprovider;
    private final GraphUseCase graphUseCase;

    @GetMapping("test")
    public String test1() {
        return "API Test 1";
    }
    // Available to managers
    @GetMapping("management/reports")
    public String reports() {
        return "API Test 2";
    }
    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public Iterable<Member> allUsers() {
        return memberRepository.findAll();
    }

    @PostMapping("signup")
    public ResponseEntity<Member> join (@RequestBody SignUpDto signDto){
        
        Member member = new Member();
        
        //get set을 사용하지 않게 수정 필요
        String encryptPw = pwEncoder.encode(signDto.getPassword());
        member.setUid(signDto.getId());
        member.setUname(signDto.getName());
        member.setPassword(encryptPw);
        member.setRoles(signDto.getRoles());
        memberRepository.save(member);

        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<ReqDto> signin (@RequestBody SignInDto signDto){
        
        Member user = memberRepository.findById(signDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("no such data"));

        UsernamePasswordAuthenticationToken authenticator = new UsernamePasswordAuthenticationToken(signDto.getId(), signDto.getPassword());
        authenticationManager.authenticate(authenticator);
       
        if (!pwEncoder.matches(signDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("no such data");
        
        List<String> list = new ArrayList<String>();
        user.getRoles().forEach(role -> list.add(role.getRoleName()));
        String token =jwttokenprovider.createToken(user.getUid(), list);   
         
        ReqDto req = new ReqDto(user.getUname(),token);

        return new ResponseEntity<>(req,HttpStatus.CREATED);
    }

    @PostMapping("joininfo")
    public ResponseEntity<Object> joininfo (@RequestBody String query){
        ExecutionResult execute = graphUseCase.execute(query);

        return new ResponseEntity<>(execute,HttpStatus.CREATED);
    }


}