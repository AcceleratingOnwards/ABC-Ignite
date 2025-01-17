package com.abcIgnite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcIgnite.model.MyClass;
import com.abcIgnite.service.ClassService;

@RestController
@RequestMapping("/classes")
public class MyClassController {

    @Autowired
    private ClassService myClassService;

    @PostMapping
    public MyClass createClass(@RequestBody MyClass newClass) {
        return myClassService.createClass(newClass);
    }

    @GetMapping
    public List<MyClass> getAllClasses() {
        return myClassService.getAllClasses();
    }
}
