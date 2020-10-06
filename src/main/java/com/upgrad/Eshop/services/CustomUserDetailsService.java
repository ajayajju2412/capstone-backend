/*
package com.upgrad.Eshop.services;

import com.upgrad.Eshop.daos.UserDAO;
import com.upgrad.Eshop.entities.CustomUserDetails;
import com.upgrad.Eshop.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> user = userDAO.findByUsername(userName);
        user.orElseThrow(
                ()-> new UsernameNotFoundException("Username not found"));
        return user.map(users -> new CustomUserDetails(users)).get();
    }
}
*/
