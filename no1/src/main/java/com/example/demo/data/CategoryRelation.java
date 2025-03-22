package com.example.demo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRelation {
    private int parentId;
    private int childId;
    
    public CategoryRelation(int parentId, int childId) {
        this.parentId = parentId;
        this.childId = childId;
    }
    
}
