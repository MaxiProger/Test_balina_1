package com.example.kolot.test_balina_1.networking.dto;

/**
 * Created by kolot on 15.01.2018.
 */

public class UserDTO {

    private String login;
    private String password;

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

