package com.abcIgnite.TestService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abcIgnite.DTO.BookingDetailsResponse;
import com.abcIgnite.model.Booking;
import com.abcIgnite.model.Member;
import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.BookingRepository;
import com.abcIgnite.repository.MemberRepository;
import com.abcIgnite.repository.MyClassRepository;
import com.abcIgnite.service.BookingService;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MyClassRepository classRepository;

    @InjectMocks
    private BookingService bookingService;

    private Member member;
    private MyClass myClass;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Member
        member = new Member();
        member.setId(1L);
        member.setName("John Doe");

        // Mock MyClass (Class)
        myClass = new MyClass();
        myClass.setId(1L);
        myClass.setName("Yoga Class");
        myClass.setStartDate(LocalDate.now());
        myClass.setEndDate(LocalDate.now().plusDays(7));
        myClass.setStartTime(LocalTime.of(10, 0));
        myClass.setDurationInMinutes(60);
        myClass.setCapacity(10);
    }

    @Test
    public void testCreateBooking_Success() {
        // Arrange
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(classRepository.findById(1L)).thenReturn(Optional.of(myClass));
        when(bookingRepository.countByMyClassId(1L)).thenReturn(5L); // Some existing bookings

        // Act
        BookingDetailsResponse response = bookingService.createBooking(1L, 1L, LocalDate.now().plusDays(1));

        // Assert
        verify(bookingRepository, times(1)).save(any(Booking.class));
        assert(response.getMemberName().equals("John Doe"));
//        assert(response.getMyClassName().equals("Yoga Class"));
        assert(response.getParticipationDate().equals(LocalDate.now().plusDays(1)));
    }

    @Test
    public void testCreateBooking_ClassCapacityExceeded() {
        // Arrange
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(classRepository.findById(1L)).thenReturn(Optional.of(myClass));
        when(bookingRepository.countByMyClassId(1L)).thenReturn(10L); // Class is at full capacity

        // Act & Assert
        try {
            bookingService.createBooking(1L, 1L, LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("Class has reached its capacity"));
        }
    }

    @Test
    public void testCreateBooking_MemberNotFound() {
        // Arrange
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());
        when(classRepository.findById(1L)).thenReturn(Optional.of(myClass));

        // Act & Assert
        try {
            bookingService.createBooking(1L, 1L, LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("Member not found"));
        }
    }

    @Test
    public void testCreateBooking_ClassNotFound() {
        // Arrange
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(classRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            bookingService.createBooking(1L, 1L, LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("Class not found"));
        }
    }
}
