package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.entite.ReservationNvl;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("reservations")

public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@PostMapping
	public ResponseEntity<Reservation> postReservation(@RequestBody @Valid ReservationNvl reservationRecu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationService.creerReservation(reservationRecu.getDateDebut(),
				reservationRecu.getDateFin(), reservationRecu.getChambres(), reservationRecu.getClientId()));

	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<String> reservationPresent(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur " + exception.getMessage());
	}

	// GET /reservations : retourne la liste des réservations au format JSON
	@GetMapping
	public List<Reservation> listerResa() {
		return this.reservationService.listerReservations();

	}

	
	

}
