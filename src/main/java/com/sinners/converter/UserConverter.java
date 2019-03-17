package com.sinners.converter;

import com.sinners.user.UserData;
import com.sinners.user.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public UserData convert(UserModel userModel) {
        UserData userData = new UserData();

        userData.setUsername(userModel.getUsername());
        userData.setEmail(userModel.getEmail());
        List<String> roles = new ArrayList<>();
        userModel.getRoles().forEach(role -> roles.add(role.toString()));
        userData.setRoles(roles);

        return userData;
    }

    public List<UserData> convert(List<UserModel> userModels) {
        List<UserData> users = new ArrayList<>();

        userModels.forEach(user -> users.add(convert(user)));

        return users;
    }
}
