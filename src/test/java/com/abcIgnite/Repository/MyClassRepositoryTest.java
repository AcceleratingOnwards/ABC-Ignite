package com.abcIgnite.Repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.MyClassRepository;

@SpringBootTest
public class MyClassRepositoryTest {

    @Mock
    private MyClassRepository myClassRepository;

    @InjectMocks
    private MyClassRepositoryTest repositoryTest;

    private MyClass myClass;
    private Long existingClassId;

    @BeforeEach
    public void setUp() {
        // Mock a MyClass object to use in test cases
        existingClassId = 1L;
        myClass = new MyClass(existingClassId, "Yoga Class", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 2, 1), 
                              LocalTime.of(10, 0), 60, 30);
    }

//    @Test
//    public void testExistsOverlappingClasses_WhenNoOverlap() {
//        LocalDate startDate = LocalDate.of(2025, 1, 1);
//
//        // Simulating no overlapping classes
//        when(myClassRepository.existsOverlappingClasses(startDate, existingClassId)).thenReturn(false);
//
//        boolean result = myClassRepository.existsOverlappingClasses(startDate, existingClassId);
//
//        assertFalse(result);
//        verify(myClassRepository, times(1)).existsOverlappingClasses(startDate, existingClassId);
//    }

//    @Test
//    public void testExistsOverlappingClasses_WhenOverlapExists() {
//        LocalDate startDate = LocalDate.of(2025, 1, 1);
//
//        // Simulating an overlap in classes
//        when(myClassRepository.existsOverlappingClasses(startDate, existingClassId)).thenReturn(true);
//
//        boolean result = myClassRepository.existsOverlappingClasses(startDate, existingClassId);
//
//        assertTrue(result);
//        verify(myClassRepository, times(1)).existsOverlappingClasses(startDate, existingClassId);
//    }

//    @Test
//    public void testExistsOverlappingClasses_WhenClassIdIsNull() {
//        LocalDate startDate = LocalDate.of(2025, 1, 1);
//
//        // Simulating checking for overlap when classId is null
//        when(myClassRepository.existsOverlappingClasses(startDate, null)).thenReturn(true);
//
//        boolean result = myClassRepository.existsOverlappingClasses(startDate, null);
//
//        assertTrue(result);
//        verify(myClassRepository, times(1)).existsOverlappingClasses(startDate, null);
//    }
//
//    @Test
//    public void testExistsOverlappingClasses_WhenNoClassesFound() {
//        LocalDate startDate = LocalDate.of(2025, 1, 1);
//
//        // Simulating no overlapping classes found
//        when(myClassRepository.existsOverlappingClasses(startDate, existingClassId)).thenReturn(false);
//
//        boolean result = myClassRepository.existsOverlappingClasses(startDate, existingClassId);
//
//        assertFalse(result);
//        verify(myClassRepository, times(1)).existsOverlappingClasses(startDate, existingClassId);
//    }
}
	