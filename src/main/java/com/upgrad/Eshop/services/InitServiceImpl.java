package com.upgrad.Eshop.services;

//import com.upgrad.Eshop.daos.UserRoleDAO;
//import com.upgrad.Eshop.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("initService")
public class InitServiceImpl implements InitService{
    /*@Autowired
    @Qualifier("userRoleDAO")
    UserRoleDAO userRoleDAO;*/

    @Override
    public void init() {
        /*Role user = new Role();
        user.setName("USER");
        Role admin = new Role();
        admin.setName("ADMIN");

        userRoleDAO.save(user);
        userRoleDAO.save(admin);*/


    }
}
