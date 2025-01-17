package com.abcIgnite.TestService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.MyClassRepository;
import com.abcIgnite.service.ClassService;

public class ClassServiceTest {

    @Mock
    private MyClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    private MyClass myClass;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        myClass = new MyClass();
        myClass.setId(1L);
        myClass.setName("Yoga Class");
        myClass.setStartDate(LocalDate.now().plusDays(1));
        myClass.setEndDate(LocalDate.now().plusDays(7));
        myClass.setCapacity(10);
    }

    @Test
    public void testCreateClass_Success() {
        when(classRepository.save(any(MyClass.class))).thenReturn(myClass);

        MyClass result = classService.createClass(myClass);

        verify(classRepository, times(1)).save(any(MyClass.class));
        assert(result.getName().equals("Yoga Class"));
        assert(result.getStartDate().equals(LocalDate.now().plusDays(1)));
        assert(result.getEndDate().equals(LocalDate.now().plusDays(7)));
        assert(result.getCapacity() == 10);
    }

    @Test
    public void testCreateClass_EmptyClassName() {
        myClass.setName("");

        try {
            classService.createClass(myClass);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Class name cannot be empty."));
        }
    }

    @Test
    public void testCreateClass_NullStartDate() {
        myClass.setStartDate(null);

        try {
            classService.createClass(myClass);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Start date cannot be null."));
        }
    }

    @Test
    public void testCreateClass_NullEndDate() {
        myClass.setEndDate(null);

        try {
            classService.createClass(myClass);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("End date cannot be null."));
        }
    }

    @Test
    public void testCreateClass_InvalidCapacity() {
        myClass.setCapacity(0);

        try {
            classService.createClass(myClass);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Capacity must be a positive integer."));
        }
    }

//    @Test
//    public void testCreateClass_EndDateBeforeNow() {
//        myClass.setEndDate(LocalDate.now().minusDays(1));
//
//        try {
//            classService.createClass(myClass);
//        } catch (RuntimeException e) {
//            assert(e.getMessage().equals("End date must be in the future."));
//        }
//    }

    @Test
    public void testCreateClass_StartDateAfterEndDate() {
        myClass.setStartDate(LocalDate.now().plusDays(2));
        myClass.setEndDate(LocalDate.now().plusDays(1));

        try {
            classService.createClass(myClass);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("The start date must be on or before the end date."));
        }
    }

//    @Test
//    public void testCreateClass_OverlappingClass() {
//        when(classRepository.existsOverlappingClasses(any(LocalDate.class), any(Long.class))).thenReturn(true);
//
//        try {
//            classService.createClass(myClass);
//        } catch (RuntimeException e) {
//            assert(e.getMessage().equals("Only one class can occur per day. A class already exists on this date."));
//        }
//    }

    @Test
    public void testGetAllClasses() {
        MyClass anotherClass = new MyClass();
        anotherClass.setId(2L);
        anotherClass.setName("Pilates Class");
        anotherClass.setStartDate(LocalDate.now().plusDays(2));
        anotherClass.setEndDate(LocalDate.now().plusDays(8));
        anotherClass.setCapacity(15);

        when(classRepository.findAll()).thenReturn(Arrays.asList(myClass, anotherClass));

        List<MyClass> classes = classService.getAllClasses();

        assert(classes.size() == 2);
        assert(classes.get(0).getName().equals("Yoga Class"));
        assert(classes.get(1).getName().equals("Pilates Class"));
    }
}
