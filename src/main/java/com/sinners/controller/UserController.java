package com.sinners.controller;

import com.sinners.facade.UserFacade;
import com.sinners.user.UserData;
import com.sinners.user.UserModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserFacade userFacade;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public List<UserData> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "registration",method = RequestMethod.POST)
    public boolean addUser(UserModel user) {
        return userFacade.addUser(user);
    }

    @RequestMapping(value = "/activation/{code}", method = RequestMethod.GET)
    public boolean activateUser(@PathVariable String code) {
        return userFacade.activateUser(code);
    }
}
