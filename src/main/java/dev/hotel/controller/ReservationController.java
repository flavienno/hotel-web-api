package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/reservations")

public class ReservationController {

	private static Logger Log = LoggerFactory.getLogger(ReservationController.class);
	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	/**
	 * @param reservationRepository
	 * @param clientRepository
	 * @param chambreRepository
	 */
	public ReservationController(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	// POST /reservations : permet de créer une réservation dans le système.
	// Si le client n’existe pas, une erreur 400 est retournée avec le message
	// "client non trouvé".
	// Si une chambre n’existe pas, une erreur 400 est retournée avec le message "la
	// chambre UUID_XXX n’existe pas

	// @PostMapping est un raccourci de @RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<String> postResa(@RequestBody ReservationNvl nvlReservation) {

		Reservation resa = new Reservation();
		List<Chambre> chambres = new ArrayList<>();
		List<UUID> chambresId = nvlReservation.getChambres();
		UUID idClient = nvlReservation.getClientId();
		Client client = new Client();

		if (!clientRepository.existsById(idClient)) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client non trouvé");

		} else {
			client = clientRepository.findById(idClient).get();
		}

		for (UUID uuid : chambresId) {

			if (!chambreRepository.existsById(uuid)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La chambre UUID= " + uuid + " n’existe pas");
			} else {
				chambres.add(chambreRepository.findById(uuid).get());
			}
		}
		if (clientRepository.existsById(idClient) && clientRepository.existsById(idClient)) {
			resa.setClient(client);
			resa.setChambres(chambres);
			resa.setDateDebut(nvlReservation.getDateDebut());
			resa.setDateFin(nvlReservation.getDateFin());
			reservationRepository.save(resa);
		}

		return ResponseEntity.status(HttpStatus.OK).body("La reservation est confirmée");
	}

	// GET /reservations : retourne la liste des réservations au format JSON
	@RequestMapping(method = RequestMethod.GET)
	public List<Reservation> retourResa() {
		List<Reservation> listResa = reservationRepository.findAll();
		return listResa;
	}

}
