package com.insoft.reservation.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.insoft.reservation.data.entity.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByLicensePlate(String licensePlate);
    
    List<Reservation> findByLicensePlateAndReservationDate(String licensePlate,
    		LocalDateTime reservationDate);
}