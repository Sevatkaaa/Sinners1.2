package com.sinners.user;

import java.util.List;

public class UserData {

    private String username;
    private String email;
    private List<String> roles;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
