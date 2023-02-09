package com.insoft.reservation.business.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insoft.reservation.data.entity.Reservation;
import com.insoft.reservation.data.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

	public List<Reservation> getReservationsForLicensePlate(String plate) {

		return this.reservationRepository.findByLicensePlate(plate);

	}

	private Boolean checkReservation(String plate, LocalDateTime date) {
		List<Reservation> reservations = this.reservationRepository.findByLicensePlateAndReservationDate(plate, date);
		return reservations.isEmpty();

	}

	public void saveReservation(String plate, String date) {
		LocalDateTime formatedDate = this.createDateFromDateString(date);
		if (Boolean.TRUE.equals(this.checkReservation(plate, formatedDate))) {
			Reservation reservation = new Reservation();
			reservation.setLicensePlate(plate);
			reservation.setReservationDate(formatedDate);
			reservation.setStatus("PROGRAMADA");
			this.reservationRepository.save(reservation);

		}

	}

	private LocalDateTime createDateFromDateString(String dateString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

		return dateTime;
	}
}
