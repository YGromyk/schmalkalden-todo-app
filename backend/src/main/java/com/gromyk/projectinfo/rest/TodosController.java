package com.gromyk.projectinfo.rest;

import com.gromyk.projectinfo.data.Mapper;
import com.gromyk.projectinfo.data.dtos.TodoDto;
import com.gromyk.projectinfo.data.entities.Todo;
import com.gromyk.projectinfo.data.entities.User;
import com.gromyk.projectinfo.data.repositories.TodoRepository;
import com.gromyk.projectinfo.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/todos")
public class TodosController {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public TodosController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email).get();
    }

    @PostMapping("/new")
    public TodoDto createTodo(@RequestBody TodoDto todo) {
        User user = getUser();
        Todo todoToSave = new Todo(todo.getTitle(), user, todo.getText());
        todoToSave = todoRepository.save(todoToSave);
        return Mapper.toTodoDto(todoToSave);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") Long todo) {
        User user = getUser();
        Optional<Todo> todoToDelete = todoRepository.findById(todo);
        todoToDelete.ifPresent(todoRepository::delete);
        return true;
    }

    @GetMapping()
    public List<TodoDto> getTodos() {
        User user = getUser();

        return todoRepository.findByOwner(user.getId()).stream().map(Mapper::toTodoDto).collect(Collectors.toList());
    }
}
