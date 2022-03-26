package com.gromyk.projectinfo.data.dtos;

import java.time.LocalDate;

public class UserRegisterDto extends UserAbstract {
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String name, String email, LocalDate birthday,  String password) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
