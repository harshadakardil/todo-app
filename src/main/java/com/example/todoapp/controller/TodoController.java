package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody Todo updatedTodo) {
        
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(updatedTodo.getTitle());
                    todo.setCompleted(updatedTodo.isCompleted());
                    Todo savedTodo = todoRepository.save(todo);
                    return ResponseEntity.ok(savedTodo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
