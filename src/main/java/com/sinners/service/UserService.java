package com.sinners.service;

import com.sinners.repository.UserRepository;
import com.sinners.user.Role;
import com.sinners.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private static final String LINK = "http://%s:8080/activation/";
    private static final String ACTIVATION_CODE = "Activation code";
    private static final String WELCOME = "Hey, %s! \n" +
            "Welcome to sinners app, share your sins with other guys and have fun!\n" +
            "To activate your account, go to link: %s%s";

    @Value("${base.url}")
    private String url;

    @Resource
    private UserRepository userRepository;

    @Resource
    private MailService mailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(UserModel user) {
        UserModel userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(WELCOME, user.getUsername(), String.format(LINK, url), user.getActivationCode());
            mailService.sendEmail(user.getEmail(), ACTIVATION_CODE, message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        UserModel user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    public List<UserModel> getAllUsers() {
        Iterable<UserModel> users = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();

        Iterator<UserModel> iterator = users.iterator();
        while (iterator.hasNext()) {
            userModels.add(iterator.next());
        }

        return userModels;
    }
}

