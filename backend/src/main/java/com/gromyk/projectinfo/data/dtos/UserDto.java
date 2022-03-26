package com.gromyk.projectinfo.data.dtos;

import java.time.LocalDate;

public class UserDto extends UserAbstract {
    public UserDto() {
    }

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }
}
