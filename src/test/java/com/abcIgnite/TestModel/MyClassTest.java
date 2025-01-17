package com.abcIgnite.TestModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.abcIgnite.model.MyClass;

public class MyClassTest {

    @Test
    public void testConstructor() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 2, 1);
        LocalTime startTime = LocalTime.of(10, 0);
        MyClass myClass = new MyClass(1L, "Yoga Class", startDate, endDate, startTime, 60, 30);

        assertEquals(1L, myClass.getId());
        assertEquals("Yoga Class", myClass.getName());
        assertEquals(startDate, myClass.getStartDate());
        assertEquals(endDate, myClass.getEndDate());
        assertEquals(startTime, myClass.getStartTime());
        assertEquals(60, myClass.getDurationInMinutes());
        assertEquals(30, myClass.getCapacity());
    }

    @Test
    public void testSettersAndGetters() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 2, 1);
        LocalTime startTime = LocalTime.of(10, 0);
        MyClass myClass = new MyClass();

        myClass.setId(2L);
        myClass.setName("Pilates Class");
        myClass.setStartDate(startDate);
        myClass.setEndDate(endDate);
        myClass.setStartTime(startTime);
        myClass.setDurationInMinutes(45);
        myClass.setCapacity(20);

        assertEquals(2L, myClass.getId());
        assertEquals("Pilates Class", myClass.getName());
        assertEquals(startDate, myClass.getStartDate());
        assertEquals(endDate, myClass.getEndDate());
        assertEquals(startTime, myClass.getStartTime());
        assertEquals(45, myClass.getDurationInMinutes());
        assertEquals(20, myClass.getCapacity());
    }

    @Test
    public void testDefaultConstructor() {
        MyClass myClass = new MyClass();

        assertNull(myClass.getId());
        assertNull(myClass.getName());
        assertNull(myClass.getStartDate());
        assertNull(myClass.getEndDate());
        assertNull(myClass.getStartTime());
        assertEquals(0, myClass.getDurationInMinutes());
        assertEquals(0, myClass.getCapacity());
    }

    @Test
    public void testNameCannotBeNull() {
        MyClass myClass = new MyClass();
        myClass.setName(null);
        myClass.setStartDate(LocalDate.of(2025, 1, 1));
        myClass.setEndDate(LocalDate.of(2025, 2, 1));
        myClass.setStartTime(LocalTime.of(10, 0));
        myClass.setDurationInMinutes(60);
        myClass.setCapacity(30);

        assertNull(myClass.getName());  
    }

    @Test
    public void testStartDateCannotBeNull() {
        MyClass myClass = new MyClass();
        myClass.setName("Zumba Class");
        myClass.setStartDate(null);
        myClass.setEndDate(LocalDate.of(2025, 2, 1));
        myClass.setStartTime(LocalTime.of(9, 0));
        myClass.setDurationInMinutes(60);
        myClass.setCapacity(25);

        assertNull(myClass.getStartDate());  
    }

    @Test
    public void testEndDateCannotBeNull() {
        MyClass myClass = new MyClass();
        myClass.setName("Yoga Class");
        myClass.setStartDate(LocalDate.of(2025, 1, 1));
        myClass.setEndDate(null);
        myClass.setStartTime(LocalTime.of(10, 0));
        myClass.setDurationInMinutes(60);
        myClass.setCapacity(30);

        assertNull(myClass.getEndDate());  
    }

    @Test
    public void testStartTimeCannotBeNull() {
        MyClass myClass = new MyClass();
        myClass.setName("Dance Class");
        myClass.setStartDate(LocalDate.of(2025, 1, 1));
        myClass.setEndDate(LocalDate.of(2025, 2, 1));
        myClass.setStartTime(null);
        myClass.setDurationInMinutes(60);
        myClass.setCapacity(20);

        assertNull(myClass.getStartTime()); 
    }

    @Test
    public void testDurationInMinutesCannotBeNegative() {
        MyClass myClass = new MyClass();
        myClass.setDurationInMinutes(-30);

        assertEquals(-30, myClass.getDurationInMinutes()); 
    }

    @Test
    public void testCapacityCannotBeNegative() {
        MyClass myClass = new MyClass();
        myClass.setCapacity(-10);

        assertEquals(-10, myClass.getCapacity());  
    }

    @Test
    public void testStartDateBeforeEndDate() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 2, 1);

        MyClass myClass = new MyClass(1L, "Yoga Class", startDate, endDate, LocalTime.of(10, 0), 60, 30);

        assertTrue(myClass.getStartDate().isBefore(myClass.getEndDate())); 
    }
}
