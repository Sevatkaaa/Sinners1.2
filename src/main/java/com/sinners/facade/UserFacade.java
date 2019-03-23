package com.sinners.facade;

import com.sinners.converter.UserConverter;
import com.sinners.service.UserService;
import com.sinners.user.UserData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserFacade {

    @Resource
    private UserService userService;

    @Resource
    private UserConverter userConverter;

    public List<UserData> getAllUsers() {
        return userConverter.convert(userService.getAllUsers());
    }

    public void addUser(String name, String password, String checkPassword, String email) {
        userService.addUser(name, password, checkPassword, email);
    }

    public void activateUser(String code) {
        userService.activateUser(code);
    }

    public UserData getUserByName(String name) {
        return userConverter.convert(userService.getUserByName(name));
    }
}
