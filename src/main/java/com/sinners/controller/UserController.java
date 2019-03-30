package com.sinners.controller;

import com.sinners.facade.UserFacade;
import com.sinners.user.UserData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController extends BaseController {

    @Resource
    private UserFacade userFacade;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public List<UserData> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "user/{name}", method = RequestMethod.GET)
    public UserData getUser(@PathVariable String name) {
        return userFacade.getUserByName(name);
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void addUser(@RequestParam String name, @RequestParam String password,
                        @RequestParam String checkPassword, @RequestParam String email) {
        userFacade.addUser(name, password, checkPassword, email);
    }

    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    public void activateUser(@RequestParam String code) {
        userFacade.activateUser(code);
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public void loginUser(@RequestParam String name,@RequestParam String password) {
        userFacade.loginUser(name, password);
    }
}
