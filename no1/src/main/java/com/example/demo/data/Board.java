package com.example.demo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private int id;
    private String name;
    private boolean isAnonymous;
    
    public Board(int id, String name, boolean isAnonymous) {
        this.id = id;
        this.name = name;
        this.isAnonymous = isAnonymous;
    }
    
    
    @Override
    public String toString() {
        return "Board{id=" + id + ", name='" + name + "', isAnonymous=" + isAnonymous + "}";
    }
}
