package com.abcIgnite.DTO;

import java.time.LocalTime;
import java.time.LocalDate;

public class BookingDetailsResponse {

    private String memberName;
    private String className;
    private LocalTime classStartTime;
    private LocalDate bookingDate;
    private LocalDate participationDate;

    public BookingDetailsResponse(String memberName, String className, LocalTime classStartTime, LocalDate bookingDate, LocalDate participationDate) {
        this.memberName = memberName;
        this.className = className;
        this.classStartTime = classStartTime;
        this.bookingDate = bookingDate;
        this.participationDate = participationDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalTime getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(LocalTime classStartTime) {
        this.classStartTime = classStartTime;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}
