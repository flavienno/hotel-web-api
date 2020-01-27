package dev.hotel.controller;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;

import dev.hotel.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	// GET /clients : retourne la liste des clients de la base de données au format
	// JSON.
	@GetMapping
	public List<Client> listerClients() {
		return this.clientService.listerClients();
	}

	// GET /clients?nom=XXX : retourne un tableau de clients dont le nom est précisé
	@GetMapping(params = "nom")
	public List<Client> rechercherParNom(@RequestParam("nom") String nomRequete) {
		return this.clientService.findByNom(nomRequete);
	}

	// POST /clients : avec un objet Client au format JSON dans le corps de la
	// requête,insère un nouveau client dans l’application.
	// Si un client possédant le même nom et les mêmes prénoms existe,
	// retourner une erreur 400 avec un message d’erreur
	@PostMapping
	public UUID creerClient(@RequestBody @Valid Client clientRecu) {
		return this.clientService.creerClient(clientRecu);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> ClientPresent(EntityExistsException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("client déja en bdd");
	}

}
