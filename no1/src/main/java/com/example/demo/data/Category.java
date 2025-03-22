package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Category {
    private int id;
    private String name;
    private List<Category> children;
    @JsonIgnore
    private Category parent;
    
    // 카테고리에 연결된 게시판 목록
    private List<Board> boards;
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
        this.boards = new ArrayList<>();
    }
    
    // 자식 카테고리 추가
    public void addChild(Category child) {
        children.add(child);
        child.setParent(this);
    }
    
    // 게시판 추가
    public void addBoard(Board board) {
        boards.add(board);
    }
    
    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + name + "'}";
    }
}
