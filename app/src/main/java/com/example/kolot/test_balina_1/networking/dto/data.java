package com.example.kolot.test_balina_1.networking.dto;

/**
 * Created by kolot on 15.01.2018.
 */

public class data {
    private int userId;
    private String login;
    private String token;

    public data(int userId, String login, String token) {
        this.userId = userId;
        this.login = login;
        this.token = token;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
