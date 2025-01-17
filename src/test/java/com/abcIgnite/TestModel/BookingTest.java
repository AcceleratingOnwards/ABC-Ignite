package com.abcIgnite.TestModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.abcIgnite.model.Booking;
import com.abcIgnite.model.Member;
import com.abcIgnite.model.MyClass;

public class BookingTest {

    @Test
    public void testConstructor() {
        Member member = new Member();
        member.setId(1L);
        MyClass myClass = new MyClass();
        myClass.setId(1L);
        LocalDate participationDate = LocalDate.of(2025, 1, 16);
        LocalDate bookingDate = LocalDate.of(2025, 1, 15);

        Booking booking = new Booking(1L, member, myClass, participationDate);
        booking.setBookingDate(bookingDate);

        assertEquals(1L, booking.getId());
        assertEquals(member, booking.getMember());
        assertEquals(myClass, booking.getMyClass());
        assertEquals(LocalDate.of(2025, 1, 16), booking.getParticipationDate());
        assertEquals(LocalDate.of(2025, 1, 15), booking.getBookingDate());
    }

    @Test
    public void testSettersAndGetters() {
        Member member = new Member();
        member.setId(1L);
        MyClass myClass = new MyClass();
        myClass.setId(1L);
        LocalDate participationDate = LocalDate.of(2025, 1, 16);
        LocalDate bookingDate = LocalDate.of(2025, 1, 15);

        Booking booking = new Booking();
        booking.setId(2L);
        booking.setMember(member);
        booking.setMyClass(myClass);
        booking.setParticipationDate(participationDate);
        booking.setBookingDate(bookingDate);

        assertEquals(2L, booking.getId());
        assertEquals(member, booking.getMember());
        assertEquals(myClass, booking.getMyClass());
        assertEquals(LocalDate.of(2025, 1, 16), booking.getParticipationDate());
        assertEquals(LocalDate.of(2025, 1, 15), booking.getBookingDate());
    }

    @Test
    public void testDefaultConstructor() {
        Booking booking = new Booking();

        assertNull(booking.getId());
        assertNull(booking.getMember());
        assertNull(booking.getMyClass());
        assertNull(booking.getParticipationDate());
        assertNull(booking.getBookingDate());
    }

    @Test
    public void testBookingDateNotNull() {
        Member member = new Member();
        member.setId(1L);
        MyClass myClass = new MyClass();
        myClass.setId(1L);
        LocalDate participationDate = LocalDate.of(2025, 1, 16);

        Booking booking = new Booking(1L, member, myClass, participationDate);
        booking.setBookingDate(LocalDate.of(2025, 1, 15));

        assertNotNull(booking.getBookingDate());
    }
}
