package org.raccoon.com.jwt.global.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.raccoon.com.jwt.global.config.jwt.CustomAccessDeniedHandler;
import org.raccoon.com.jwt.global.config.jwt.CustomAuthenticationEntryPoint;
import org.raccoon.com.jwt.global.config.jwt.JwtAuthenticationFilter;
import org.raccoon.com.jwt.global.config.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증할것이므로
                                                                                            // 세션필요없으므로 생성안함.
                .and().authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/*/signin", "/*/signin/**", "/*/signup", "/*/signup/**", "/social/**","/*/joininfo/**").permitAll() // 가입 및
                                                                                                                 // 인증
                                                                                                                 // 주소는
                                                                                                                 // 누구나
                                                                                                                 // 접근가능
                .antMatchers(HttpMethod.GET, "/exception/**", "/helloworld/**", "/actuator/health").permitAll() // 등록된
                                                                                                                // GET요청
                                                                                                                // 리소스는
                                                                                                                // 누구나
                                                                                                                // 접근가능
                .anyRequest().hasRole("MANAGER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()).and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣어라.

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.info("build Auth global........");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}