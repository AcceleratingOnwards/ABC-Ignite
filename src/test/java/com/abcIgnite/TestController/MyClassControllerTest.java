package com.abcIgnite.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abcIgnite.controller.MyClassController;
import com.abcIgnite.model.MyClass;
import com.abcIgnite.service.ClassService;

@ExtendWith(MockitoExtension.class)
class MyClassControllerTest {

    @Mock
    private ClassService myClassService;

    @InjectMocks
    private MyClassController myClassController;

    @Test
    void testCreateClass_Success() {
        MyClass newClass = new MyClass();
        newClass.setId(1L);
        newClass.setName("Yoga");
        newClass.setStartDate(LocalDate.of(2025, 1, 1));
        newClass.setEndDate(LocalDate.of(2025, 1, 31));
        newClass.setStartTime(LocalTime.of(6, 0));
        newClass.setDurationInMinutes(60);
        newClass.setCapacity(20);

        when(myClassService.createClass(newClass)).thenReturn(newClass);

        MyClass createdClass = myClassController.createClass(newClass);

        assertNotNull(createdClass);
        assertEquals("Yoga", createdClass.getName());
        assertEquals(LocalDate.of(2025, 1, 1), createdClass.getStartDate());
        assertEquals(LocalDate.of(2025, 1, 31), createdClass.getEndDate());
        assertEquals(LocalTime.of(6, 0), createdClass.getStartTime());
        assertEquals(60, createdClass.getDurationInMinutes());
        assertEquals(20, createdClass.getCapacity());

        verify(myClassService, times(1)).createClass(newClass);
    }

    @Test
    void testGetAllClasses_Success() {
        MyClass class1 = new MyClass(1L, "Yoga", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31), LocalTime.of(6, 0), 60, 20);
        MyClass class2 = new MyClass(2L, "Pilates", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 28), LocalTime.of(7, 0), 45, 15);
        List<MyClass> classes = Arrays.asList(class1, class2);

        when(myClassService.getAllClasses()).thenReturn(classes);

        List<MyClass> response = myClassController.getAllClasses();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Yoga", response.get(0).getName());
        assertEquals("Pilates", response.get(1).getName());

        verify(myClassService, times(1)).getAllClasses();
    }

    @Test
    void testGetAllClasses_EmptyList() {
        when(myClassService.getAllClasses()).thenReturn(Arrays.asList());

        List<MyClass> response = myClassController.getAllClasses();

        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(myClassService, times(1)).getAllClasses();
    }
}

