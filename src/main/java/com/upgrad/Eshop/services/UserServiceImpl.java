package com.upgrad.Eshop.services;

import com.upgrad.Eshop.daos.UserDAO;
import com.upgrad.Eshop.entities.Users;
import com.upgrad.Eshop.exceptions.DuplicateEmailException;
import com.upgrad.Eshop.exceptions.UserDetailsNotFoundException;
import com.upgrad.Eshop.exceptions.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    //private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //POST methods
    //in usage
    public Users saveUser(Users users) throws UsernameExistsException, DuplicateEmailException {
        //logger.debug("Entered saveUser", users);
        /*if (userDAO.findByEmail(users.getEmail()) != null) {
            throw new DuplicateEmailException("Email already registered :" + users.getEmail());
        } else if (userDAO.findByUsername(users.getUserName()) != null) {
            throw new UsernameExistsException("Username already exists : " + users.getUserName());
        } else {
            return userDAO.save(users);
        }*/

        return userDAO.save(users);
    }
    public List<Users> saveUsers(List<Users> users) {
        return userDAO.saveAll(users);
    }


    //GET methods
    public List<Users> getUsers() {
        return userDAO.findAll();
    }
    //in usage
    public Users getUserById(int id) throws UserDetailsNotFoundException {
        Users users = userDAO.findById(id).orElseThrow(
                () -> new UserDetailsNotFoundException("Users not found for id" + id)
        );
        return users;
    }
    //in usage
    /*public Users getUserByUsername(String userName) throws UserDetailsNotFoundException {
        Users users = userDAO.findByUsername(userName).orElseThrow(
                () -> new UserDetailsNotFoundException("Users not found for userName: " + userName)
        );
        return users;
    }*/
    //replica for above
    public Users getUserByUsername(String username){
        Users users = userDAO.findByUsername(username).orElse(null);
        return users;
    }
    //in usage
    /*public UserDetails loadUser(String userName) {
        Users users = userDAO.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException("Users not found for userName: " + userName)
        );
        return new User(users.getUserName(), users.getPassword(), new ArrayList<>());
    }*/


    //PUT method
    public Users updateUser(Users users) {
        Users existingUsers = userDAO.findById(users.getId()).orElse(null);
        existingUsers.setCreated(users.getCreated());
        existingUsers.setEmail(users.getEmail());
        existingUsers.setFirstName(users.getFirstName());
        existingUsers.setLastName(users.getLastName());
        existingUsers.setPassword(users.getPassword());
        existingUsers.setPhoneNumber(users.getPhoneNumber());
        existingUsers.setRole(users.getRole());
        existingUsers.setUsername(users.getUsername());
        existingUsers.setUpdated(users.getUpdated());
        return userDAO.save(existingUsers);
    }

    //DELETE method
    public String deleteUserById(int id) {
        userDAO.deleteById(id);
        return "Users deleted " + id;
    }

    public Users getUserByEmail(String email){
        /*Users users = Users users = userDAO.findByEmail(email).orElseThrowThrow(
                () -> new UserDetailsNotFoundException("Users not found for email: " + email)
        );*/
        return userDAO.findByEmail(email).orElse(null);
    }

}
