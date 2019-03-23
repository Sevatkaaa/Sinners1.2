package com.sinners.service;

import com.sinners.exception.EmailSendingException;
import com.sinners.exception.InvalidEmailException;
import com.sinners.exception.UserNotFoundException;
import com.sinners.repository.UserRepository;
import com.sinners.user.Role;
import com.sinners.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;

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
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(String name, String password, String email) {
        validateEmail(email);
        
        UserModel userFromDB = userRepository.findByUsername(name);
        if (userFromDB != null) {
            throw new UserNotFoundException();
        }
        UserModel user = new UserModel();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        if (!StringUtils.isEmpty(user.getEmail())) {
            try {
                String message = String.format(WELCOME, user.getUsername(), String.format(LINK, url), user.getActivationCode());
                mailService.sendEmail(user.getEmail(), ACTIVATION_CODE, message);
            } catch (Exception e) {
                throw new EmailSendingException(e);
            }
        }

        userRepository.save(user);
    }

    public void activateUser(String code) {
        UserModel user = userRepository.findByActivationCode(code);
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
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

    private void validateEmail(String email) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        if (!p.matcher(email).find()) {
            throw new InvalidEmailException();
        }
    }
}

