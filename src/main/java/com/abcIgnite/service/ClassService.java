package com.abcIgnite.service;

import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.MyClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClassService {

    @Autowired
    private MyClassRepository classRepository;

    public MyClass createClass(MyClass newClass) {
        validateClass(newClass, null);
        return classRepository.save(newClass);
    }



    public List<MyClass> getAllClasses() {
        return classRepository.findAll();
    }


    private void validateClass(MyClass classToValidate, Long classIdToExclude) {
        if (classToValidate.getName() == null || classToValidate.getName().isEmpty()) {
            throw new RuntimeException("Class name cannot be empty.");
        }
        if (classToValidate.getStartDate() == null) {
            throw new RuntimeException("Start date cannot be null.");
        }
        if (classToValidate.getEndDate() == null) {
            throw new RuntimeException("End date cannot be null.");
        }
        if (classToValidate.getCapacity() <= 0) {
            throw new RuntimeException("Capacity must be a positive integer.");
        }
        if (classToValidate.getStartDate().isAfter(classToValidate.getEndDate())) {
            throw new RuntimeException("The start date must be on or before the end date.");
        }
        if (classToValidate.getEndDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("End date must be in the future.");
        }


    }

}
