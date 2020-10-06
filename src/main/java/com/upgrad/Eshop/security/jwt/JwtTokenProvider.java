package com.upgrad.Eshop.security.jwt;

import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.InvalidTokenException;
import com.upgrad.Eshop.exceptions.UserDetailsNotFoundException;
import com.upgrad.Eshop.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Autowired
    private UserService userService;

    private String secretKey = "secret";
    private long validityInMilliseconds = 3600000;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //user requesting for token
    public String createToken(String credentials) {
        Claims claims = Jwts.claims().setSubject(credentials);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //user sends a token
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //read token from header
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("X-Access-Token");
        if (bearerToken != null) {
            return bearerToken.trim();
        }
        return null;
    }

    //validate token
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Expired or invalid JWT token");
        }
    }

    //authenticate token
    public Authentication getAuthentication(String token) throws UserDetailsNotFoundException {
       /* UserDetails userDetails = this.customerService.loadCustomerDetails(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());*/

        String username =getUsername(token);
        Users users = userService.getUserByUsername(username);

        if(users == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(null,"",null);

    }

}
