package com.gromyk.projectinfo.data;

import com.gromyk.projectinfo.data.dtos.TodoDto;
import com.gromyk.projectinfo.data.dtos.UserDto;
import com.gromyk.projectinfo.data.dtos.UserRegisterDto;
import com.gromyk.projectinfo.data.entities.Todo;
import com.gromyk.projectinfo.data.entities.User;

public class Mapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


    public static User toUser(UserRegisterDto user, String encodedPassword) {
        return new User(user.getName(),
                user.getEmail(),
                encodedPassword
        );
    }

    public static TodoDto toTodoDto(Todo todo) {
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getCreatedAt(),
                todo.getText(),
                todo.getOwner().getId()
        );
    }
}
