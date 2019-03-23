package com.sinners.facade;

import com.sinners.converter.UserConverter;
import com.sinners.service.UserService;
import com.sinners.user.UserData;
import com.sinners.user.UserModel;
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

    public void addUser(String name, String password, String email) {
        userService.addUser(name, password, email);
    }

    public void activateUser(String code) {
        userService.activateUser(code);
    }
}
