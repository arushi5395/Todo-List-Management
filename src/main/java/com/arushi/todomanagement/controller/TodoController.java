package com.arushi.todomanagement.controller;

import com.arushi.todomanagement.dto.TodoDto;
import com.arushi.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
@CrossOrigin("*")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
        TodoDto savedTodo = todoService.getTodoById(id);
        return ResponseEntity.ok(savedTodo);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodoById(@PathVariable Long id, @RequestBody TodoDto updatedTodo){
        TodoDto savedTodo = todoService.updateTodoById(id, updatedTodo);
        return ResponseEntity.ok(savedTodo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> removeTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return ResponseEntity.ok("Given Todo is deleted!");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
