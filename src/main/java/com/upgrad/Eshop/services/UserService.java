package com.upgrad.Eshop.services;

import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.DuplicateEmailException;
import com.upgrad.Eshop.exceptions.UserDetailsNotFoundException;
import com.upgrad.Eshop.exceptions.UsernameExistsException;

import java.util.List;

public interface UserService {
    public Users saveUser(Users users) throws UsernameExistsException, DuplicateEmailException;
    public List<Users> saveUsers(List<Users> users);
    public List<Users> getUsers();
    public Users getUserById(int id) throws UserDetailsNotFoundException;
    public Users getUserByUsername(String username) throws UserDetailsNotFoundException;
    public Users updateUser(Users users);
    public String deleteUserById(int id);

    public Users getUserByEmail(String email) throws UserDetailsNotFoundException;
}
