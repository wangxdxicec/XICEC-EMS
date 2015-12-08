package com.zhenhappy.ems.dto;

/**
 * Created by wujianbin on 2014-04-04.
 */
public class LoginRequest extends BaseRequest{

    private String username;

    private String password;

    private boolean english;

    private Integer area;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public boolean isEnglish() {
        return english;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
