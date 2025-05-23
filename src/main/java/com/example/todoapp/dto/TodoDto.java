package com.example.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoDto {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private boolean completed;
}