package com.insoft.reservation.web.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.insoft.reservation.business.service.ReservationService;
import com.insoft.reservation.data.entity.Reservation;
import com.insoft.reservation.web.dto.RequestDTO;
import com.insoft.reservation.web.dto.ReservationDTO;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping("/reservation")
	public String postReservations(@ModelAttribute RequestDTO requestDTO, Model model) {

		List<Reservation> reservationsList = reservationService.getReservationsForLicensePlate(requestDTO.getPlate());
		model.addAttribute("reservationsList", reservationsList);
		return "reservations";
	}

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("requestDTO", new RequestDTO());
		return "home";
	}

	@GetMapping("/newreservation")
	public String getNewReservation(Model model) {
		model.addAttribute("reservationDTO", new ReservationDTO());
		return "newreservation";
	}

	@PostMapping("/newreservation")
	public String postReservation(@ModelAttribute ReservationDTO reservationDTO, Model model) {

		String fechaReserva = reservationDTO.getDate() + " " + reservationDTO.getHour() + ":"
				+ reservationDTO.getMinute();
		reservationService.saveReservation(reservationDTO.getPlate(), fechaReserva);
		model.addAttribute("requestDTO", new RequestDTO());

		return "home";
	}

	@GetMapping("/reservations")
	public String getReservations(@ModelAttribute RequestDTO requestDTO, Model model) {

		List<Reservation> reservationsList = new ArrayList<>();
		model.addAttribute("reservationsList", reservationsList);
		return "reservations";
	}

}
