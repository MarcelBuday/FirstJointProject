package com.example.todo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // Toto je potrebne
@Data
@NoArgsConstructor
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generuje to unikatne ID do DB
    @Id // toto je potrebne
    private long id;
    private String title;
    private  boolean urgent;
    private  boolean done;

    public Todo(String title, boolean urgent) {
        this.title = title;
        this.urgent = urgent;
    }
}