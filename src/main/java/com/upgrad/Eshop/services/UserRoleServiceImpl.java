/*
package com.upgrad.Eshop.services;

import com.upgrad.Eshop.daos.UserRoleDAO;
import com.upgrad.Eshop.entities.Role;
import com.upgrad.Eshop.exceptions.UserRoleDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    UserRoleDAO userRoleDAO;

    @Override
    public Role saveUserRoleDetails(Role role) {
        return userRoleDAO.save(role);
    }

    @Override
    public Role getUserRoleDetails(int id) throws UserRoleDetailsNotFoundException {
        return userRoleDAO.findById(id).orElseThrow(
                ()->  new UserRoleDetailsNotFoundException("Role not found for " + id));
    }
}
*/
