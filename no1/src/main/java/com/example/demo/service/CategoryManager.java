package com.example.demo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.data.Board;
import com.example.demo.data.Category;
import com.example.demo.data.CategoryRelation;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
@Scope("singleton")
public class CategoryManager implements Serializable {
    private static final long serialVersionUID = 1L;

    // 카테고리 관계 관리 클래스
    private Map<Integer, Category> categoriesById;
    private Map<String, List<Category>> categoriesByName;
    private Category root;
    //private ObjectMapper objectMapper;
        
    
    public CategoryManager() {
        //this.objectMapper = objectMapper;
        this.categoriesById = new HashMap<>();
        this.categoriesByName = new HashMap<>();
    
        // 루트 카테고리 생성
        this.root = new Category(0, "Root");
        categoriesById.put(0, root);
        addToCategoryNameMap(root);    
    }
    
    // ID로 카테고리 가져오기
    public Category getCategoryById(int id) {
        return categoriesById.get(id);
    }
    
    // 이름으로 카테고리 목록 가져오기
    public List<Category> getCategoriesByName(String name) {
        return categoriesByName.getOrDefault(name, new ArrayList<>());
    }
    
    // 루트 카테고리 가져오기
    public Category getRoot() {
        return root;
    }
    
    // 카테고리 추가
    public void addCategory(int id, String name, int parentId) {
        Category category = new Category(id, name);
        categoriesById.put(id, category);
        addToCategoryNameMap(category);
        
        // 부모 카테고리에 자식으로 추가
        Category parent = getCategoryById(parentId);
        if (parent != null) {
            parent.addChild(category);
        }
    }
    
    // 게시판 추가
    public void addBoard(int boardId, String boardName, boolean isAnonymous, int categoryId) {
        Board board = new Board(boardId, boardName, isAnonymous);
        Category category = getCategoryById(categoryId);
        if (category != null) {
            category.addBoard(board);
        }
    }
    
    // 카테고리 이름으로 검색을 위한 맵에 추가
    private void addToCategoryNameMap(Category category) {
        String name = category.getName();
        if (!categoriesByName.containsKey(name)) {
            System.out.println("새 맵 추가");
            List<Category> newNameMap = new ArrayList<>();
            newNameMap.add(category);
            categoriesByName.put(name, newNameMap);
        }else{
            categoriesByName.get(name).add(category);
        }
        
    }
    
    // 특정 카테고리와 그 하위 카테고리를 JSON 형태로 반환
    public String getCategoryTreeAsJson(int categoryId) {
        try {
            Category category = getCategoryById(categoryId);
            if (category == null) {
                return "{}";
            }
            
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(category);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
    
    // 전체 카테고리 구조를 JSON 형태로 반환
    public String getAllCategoriesAsJson() {
        return getCategoryTreeAsJson(0); // 루트부터 시작
    }
    
    // 관계 데이터를 저장하고 불러오는 메서드
    public List<CategoryRelation> exportRelations() {
        List<CategoryRelation> relations = new ArrayList<>();
        
        // 모든 카테고리에 대해 부모-자식 관계 추출
        for (Category category : categoriesById.values()) {
            if (category.getParent() != null) {
                relations.add(new CategoryRelation(
                    category.getParent().getId(), 
                    category.getId()
                ));
            }
        }
        
        return relations;
    }
    
    // 관계 데이터로부터 카테고리 구조 복원
    public void importRelations(List<CategoryRelation> relations) {
        for (CategoryRelation relation : relations) {
            Category parent = categoriesById.get(relation.getParentId());
            Category child = categoriesById.get(relation.getChildId());
            
            if (parent != null && child != null) {
                parent.addChild(child);
            }
        }
    }

}
