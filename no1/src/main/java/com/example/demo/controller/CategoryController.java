package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CategoryManager;

import lombok.RequiredArgsConstructor;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryManager categoryManager;

    @GetMapping("/category")
    public ResponseEntity<?> getCategory(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id) {
        if(name==null && id == null) return new ResponseEntity<String>(categoryManager.getAllCategoriesAsJson(), HttpStatus.OK);
        else if(name == null) return new ResponseEntity<String>(categoryManager.getCategoryTreeAsJson(id), HttpStatus.OK);
        else return new ResponseEntity<>(categoryManager.getCategoriesByName(name), HttpStatus.OK);
    }
    
    
    
}
