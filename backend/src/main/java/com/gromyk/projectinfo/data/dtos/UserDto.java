package com.gromyk.projectinfo.data.dtos;

import com.gromyk.projectinfo.data.entities.Sex;

import java.time.LocalDate;

public class UserDto extends UserAbstract {
    public UserDto() {
    }

    public UserDto(Long id, String name, String email, LocalDate birthday, Sex sex) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
    }
}
