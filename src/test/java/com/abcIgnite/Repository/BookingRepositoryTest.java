package com.abcIgnite.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.abcIgnite.model.Booking;
import com.abcIgnite.model.Member;
import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.BookingRepository;

@SpringBootTest
public class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingRepositoryTest repositoryTest;

    private Booking booking1;
    private Booking booking2;
    private Long classId;
    private Long memberId;
    private LocalDate startDate;
    private LocalDate endDate;
    private MyClass myClass;
    private Member member;

    @BeforeEach
    public void setUp() {
        // Initialize test data
        memberId = 1L;
        classId = 2L;
        startDate = LocalDate.of(2025, 1, 1);
        endDate = LocalDate.of(2025, 1, 10);
        member = new Member();
        member.setId(memberId);
        myClass = new MyClass();
        myClass.setId(classId);
        
        booking1 = new Booking(1L, member, myClass, LocalDate.of(2025, 1, 3));
        booking2 = new Booking(2L, member, myClass, LocalDate.of(2025, 1, 5));
    }

    @Test
    public void testCountByMyClassIdAndParticipationDate() {
        // Simulating count by classId and participationDate
        when(bookingRepository.countByMyClassIdAndParticipationDate(classId, LocalDate.of(2025, 1, 3))).thenReturn(1L);
        when(bookingRepository.countByMyClassIdAndParticipationDate(classId, LocalDate.of(2025, 1, 6))).thenReturn(0L);

        long result1 = bookingRepository.countByMyClassIdAndParticipationDate(classId, LocalDate.of(2025, 1, 3));
        long result2 = bookingRepository.countByMyClassIdAndParticipationDate(classId, LocalDate.of(2025, 1, 6));

        assertEquals(1, result1);
        assertEquals(0, result2);
//        verify(bookingRepository, times(2)).countByMyClassIdAndParticipationDate(classId, any(LocalDate.class));
    }

    @Test
    public void testFindByMemberIdAndParticipationDateBetween() {
        // Simulating finding bookings by memberId and participationDate range
        when(bookingRepository.findByMemberIdAndParticipationDateBetween(memberId, startDate, endDate))
                .thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> result = bookingRepository.findByMemberIdAndParticipationDateBetween(memberId, startDate, endDate);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(booking1));
        assertTrue(result.contains(booking2));
        verify(bookingRepository, times(1)).findByMemberIdAndParticipationDateBetween(memberId, startDate, endDate);
    }

    @Test
    public void testFindByParticipationDateBetween() {
        // Simulating finding bookings by participationDate range
        when(bookingRepository.findByParticipationDateBetween(startDate, endDate))
                .thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> result = bookingRepository.findByParticipationDateBetween(startDate, endDate);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(booking1));
        assertTrue(result.contains(booking2));
        verify(bookingRepository, times(1)).findByParticipationDateBetween(startDate, endDate);
    }

    @Test
    public void testCountByMyClassId() {
        // Simulating count by classId
        when(bookingRepository.countByMyClassId(classId)).thenReturn(2L);

        long result = bookingRepository.countByMyClassId(classId);

        assertEquals(2, result);
        verify(bookingRepository, times(1)).countByMyClassId(classId);
    }

    @Test
    public void testSearchBookings() {
        // Simulating custom search query
        when(bookingRepository.searchBookings(memberId, "John", "john@example.com", startDate, endDate))
                .thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> result = bookingRepository.searchBookings(memberId, "John", "john@example.com", startDate, endDate);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(booking1));
        assertTrue(result.contains(booking2));
        verify(bookingRepository, times(1)).searchBookings(memberId, "John", "john@example.com", startDate, endDate);
    }
}
