package com.insoft.reservation.data.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "RESERVATION")
@Getter
@Setter
@NoArgsConstructor

public class Reservation {

	@Id
	@Column(name = "RESERVATION_ID")
	private String id;
	@Column(name = "LICENSE_PLATE")
	private String licensePlate;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "RES_DATE")
	private LocalDateTime reservationDate;

	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
	}
}