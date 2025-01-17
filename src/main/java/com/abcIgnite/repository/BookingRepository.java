package com.abcIgnite.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abcIgnite.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    long countByMyClassIdAndParticipationDate(Long classId, LocalDate participationDate);

    List<Booking> findByMemberIdAndParticipationDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);

    List<Booking> findByParticipationDateBetween(LocalDate startDate, LocalDate endDate);


	long countByMyClassId(Long myClassId);
	

    @Query("SELECT b FROM Booking b " +
            "WHERE (:memberId IS NULL OR b.member.id = :memberId) " +
            "AND (:memberName IS NULL OR b.member.name LIKE %:memberName%) " +
            "AND (:memberEmail IS NULL OR b.member.email LIKE %:memberEmail%) " +
            "AND (:startDate IS NULL OR b.bookingDate >= :startDate) " +
            "AND (:endDate IS NULL OR b.bookingDate <= :endDate)")
    List<Booking> searchBookings(Long memberId, String memberName, String memberEmail, LocalDate startDate, LocalDate endDate);

}
