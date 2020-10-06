package com.upgrad.Eshop.daos;

import com.upgrad.Eshop.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userDAO")
public interface UserDAO extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
