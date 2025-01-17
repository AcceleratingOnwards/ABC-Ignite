package com.abcIgnite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abcIgnite.DTO.BookingDetailsResponse;
import com.abcIgnite.model.Booking;
import com.abcIgnite.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDetailsResponse> createBooking(@RequestParam Long memberId,
                                                 @RequestParam Long classId,
                                                 @RequestParam LocalDate participationDate) {
        try {

            BookingDetailsResponse bookingResponse = bookingService.createBooking(memberId, classId, participationDate);
            return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<BookingDetailsResponse>> searchBookings(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) String memberEmail,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        List<BookingDetailsResponse> bookings = bookingService.searchBookings(
                memberId, memberName, memberEmail, startDate, endDate);

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
