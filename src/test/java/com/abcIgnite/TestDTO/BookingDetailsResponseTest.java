package com.abcIgnite.TestDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.abcIgnite.DTO.BookingDetailsResponse;

public class BookingDetailsResponseTest {

    @Test
    public void testConstructor() {
        String memberName = "John Doe";
        String className = "Yoga";
        LocalTime classStartTime = LocalTime.of(10, 0);
        LocalDate bookingDate = LocalDate.of(2025, 1, 16);
        LocalDate participationDate = LocalDate.of(2025, 1, 17);

        BookingDetailsResponse response = new BookingDetailsResponse(memberName, className, classStartTime, bookingDate, participationDate);

        assertEquals("John Doe", response.getMemberName());
        assertEquals("Yoga", response.getClassName());
        assertEquals(LocalTime.of(10, 0), response.getClassStartTime());
        assertEquals(LocalDate.of(2025, 1, 16), response.getBookingDate());
        assertEquals(LocalDate.of(2025, 1, 17), response.getParticipationDate());
    }

    @Test
    public void testSettersAndGetters() {
        BookingDetailsResponse response = new BookingDetailsResponse("John Doe", "Yoga", LocalTime.of(10, 0), LocalDate.of(2025, 1, 16), LocalDate.of(2025, 1, 17));

        response.setMemberName("Jane Doe");
        response.setClassName("Pilates");
        response.setClassStartTime(LocalTime.of(12, 0));
        response.setBookingDate(LocalDate.of(2025, 1, 18));
        response.setParticipationDate(LocalDate.of(2025, 1, 19));

        assertEquals("Jane Doe", response.getMemberName());
        assertEquals("Pilates", response.getClassName());
        assertEquals(LocalTime.of(12, 0), response.getClassStartTime());
        assertEquals(LocalDate.of(2025, 1, 18), response.getBookingDate());
        assertEquals(LocalDate.of(2025, 1, 19), response.getParticipationDate());
    }

    @Test
    public void testEmptyConstructor() {
        BookingDetailsResponse response = new BookingDetailsResponse("", "", null, null, null);

        assertNull(response.getClassStartTime());
        assertNull(response.getBookingDate());
        assertNull(response.getParticipationDate());
        assertEquals("", response.getMemberName());
        assertEquals("", response.getClassName());
    }
}
