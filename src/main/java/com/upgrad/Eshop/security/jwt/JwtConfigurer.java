package com.upgrad.Eshop.security.jwt;


import com.upgrad.Eshop.exceptions.AuthenticationExceptionHandler;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Moto-configuring filter to come at particular stage and handle exceptions
public class JwtConfigurer extends SecurityConfigurerAdapter<org.springframework.security.web.DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    public JwtConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter filter = new JwtTokenFilter(jwtTokenProvider);
        ExceptionTranslationFilter exceptionTranslationFilter= new ExceptionTranslationFilter(new AuthenticationExceptionHandler());

        //http.addFilterAfter(customFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(filter, ExceptionTranslationFilter.class);
    }
}
