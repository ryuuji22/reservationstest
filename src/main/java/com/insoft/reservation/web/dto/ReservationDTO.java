package com.insoft.reservation.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {

	private String plate;
	private String date;
	private String hour;
	private String minute;

}
