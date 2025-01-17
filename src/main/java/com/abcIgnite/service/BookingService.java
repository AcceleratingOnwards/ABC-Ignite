package com.abcIgnite.service;

import com.abcIgnite.DTO.BookingDetailsResponse;
import com.abcIgnite.model.Booking;
import com.abcIgnite.model.Member;
import com.abcIgnite.model.MyClass;
import com.abcIgnite.repository.BookingRepository;
import com.abcIgnite.repository.MyClassRepository;
import com.abcIgnite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MyClassRepository classRepository;

    

    public BookingDetailsResponse createBooking(Long memberId, Long myClassId, LocalDate participationDate) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        MyClass myClass = classRepository.findById(myClassId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found"));


        long currentBookingCount = bookingRepository.countByMyClassId(myClassId);
        if (currentBookingCount >= myClass.getCapacity()) {
            throw new IllegalArgumentException("Class has reached its capacity");
        }

        Booking booking = new Booking();
        booking.setMember(member);
        booking.setMyClass(myClass);
        booking.setParticipationDate(participationDate);
        booking.setBookingDate(LocalDate.now());

        Booking savedBooking = bookingRepository.save(booking);


        return new BookingDetailsResponse(
                member.getName(),
                myClass.getName(),
                myClass.getStartTime(),
                LocalDate.now(),
                participationDate
        );
    }



    public List<BookingDetailsResponse> searchBookings(Long memberId, String memberName, String memberEmail, LocalDate startDate, LocalDate endDate) {
        List<Booking> bookings = bookingRepository.searchBookings(memberId, memberName, memberEmail, startDate, endDate);


        return bookings.stream().map(booking -> {
            return new BookingDetailsResponse(
                    booking.getMember().getName(),
                    booking.getMyClass().getName(),
                    booking.getMyClass().getStartTime(),
                    booking.getBookingDate(),
                    booking.getParticipationDate()
            );
        }).collect(Collectors.toList());
    }
}


