package model;

import lombok.Data;

@Data
public class LoginResponseModel {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
