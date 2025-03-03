package com.reactproject.startupinvestor.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String email;
    private String password;
    private String phone;
    private String city;
    private String country;
    private String userType;
    private String opt;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
