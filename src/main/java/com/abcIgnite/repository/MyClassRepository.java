package com.abcIgnite.repository;

import com.abcIgnite.model.MyClass;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyClassRepository extends JpaRepository<MyClass, Long> {

}
