package com.example.happytail.models;

public class LoginResponse {
    private Long userId;
    private String userName;
    private String userAva;

    public LoginResponse(Long userId, String userName, String userAva) {
        this.userId = userId;
        this.userName = userName;
        this.userAva = userAva;
    }

    // Геттеры и сеттеры
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAva() {
        return userAva;
    }

    public void setUserAva(String userAva) {
        this.userAva = userAva;
    }
}
