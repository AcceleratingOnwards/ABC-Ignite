package com.abcIgnite.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abcIgnite.DTO.BookingDetailsResponse;
import com.abcIgnite.controller.BookingController;
import com.abcIgnite.service.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    void testCreateBooking_Success() {
        Long memberId = 1L;
        Long classId = 101L;
        LocalDate participationDate = LocalDate.of(2025, 1, 16);
        BookingDetailsResponse bookingResponse = new BookingDetailsResponse("John Doe", "Yoga", LocalTime.of(10, 0), LocalDate.of(2025, 1, 16), participationDate);

        when(bookingService.createBooking(memberId, classId, participationDate)).thenReturn(bookingResponse);

        ResponseEntity<BookingDetailsResponse> response = bookingController.createBooking(memberId, classId, participationDate);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookingResponse, response.getBody());

        verify(bookingService, times(1)).createBooking(memberId, classId, participationDate);
    }

    @Test
    void testCreateBooking_BadRequest() {
        Long memberId = 1L;
        Long classId = 101L;
        LocalDate participationDate = LocalDate.of(2025, 1, 16);

        when(bookingService.createBooking(memberId, classId, participationDate)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<BookingDetailsResponse> response = bookingController.createBooking(memberId, classId, participationDate);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(bookingService, times(1)).createBooking(memberId, classId, participationDate);
    }

    @Test
    void testSearchBookings_Success() {
        Long memberId = 1L;
        String memberName = "John Doe";
        String memberEmail = "johndoe@example.com";
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 31);
        BookingDetailsResponse booking1 = new BookingDetailsResponse("John Doe", "Yoga", LocalTime.of(10, 0), LocalDate.of(2025, 1, 16), LocalDate.of(2025, 1, 17));
        BookingDetailsResponse booking2 = new BookingDetailsResponse("John Doe", "Pilates", LocalTime.of(11, 0), LocalDate.of(2025, 1, 18), LocalDate.of(2025, 1, 19));
        List<BookingDetailsResponse> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.searchBookings(memberId, memberName, memberEmail, startDate, endDate)).thenReturn(bookings);

        ResponseEntity<List<BookingDetailsResponse>> response = bookingController.searchBookings(memberId, memberName, memberEmail, startDate, endDate);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getMemberName());
        assertEquals("Yoga", response.getBody().get(0).getClassName());

        verify(bookingService, times(1)).searchBookings(memberId, memberName, memberEmail, startDate, endDate);
    }

    @Test
    void testSearchBookings_NoResults() {
        Long memberId = 1L;
        String memberName = "Jane Smith";
        String memberEmail = "janesmith@example.com";
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 31);

        when(bookingService.searchBookings(memberId, memberName, memberEmail, startDate, endDate)).thenReturn(Arrays.asList());

        ResponseEntity<List<BookingDetailsResponse>> response = bookingController.searchBookings(memberId, memberName, memberEmail, startDate, endDate);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());

        verify(bookingService, times(1)).searchBookings(memberId, memberName, memberEmail, startDate, endDate);
    }
}
