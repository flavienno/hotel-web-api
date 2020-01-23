package dev.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.conditional.IfAction;
import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public ClientController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	// GET /clients : retourne la liste des clients de la base de données au format
	// JSON.
	@RequestMapping(method = RequestMethod.GET)
	public List<Client> retourClients() {
		List<Client> listClients = clientRepository.findAll();
		return listClients;
	}

	// GET /clients?nom=XXX : retourne un tableau de clients dont le nom est précisé
	@RequestMapping(method = RequestMethod.GET, params = "nom")
	public Client[] rechercheParNom(@RequestParam("nom") String nomRequeteHttp) {
		return clientRepository.findByNom(nomRequeteHttp);
	}

	// POST /clients : avec un objet Client au format JSON dans le corps de la
	// requête,insère un nouveau client dans l’application.
	// Si un client possédant le même nom et les mêmes prénoms existe,
	// retourner une erreur 400 avec un message d’erreur
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postClient(@RequestBody Client nvclient) {

		if (clientRepository.existsByNomAndPrenoms(nvclient.getNom(), nvclient.getPrenoms()) == false) {
			clientRepository.save(nvclient);
			return ResponseEntity.status(HttpStatus.OK).body("Le client à bien été inséré");

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le client existe déjà");

		}

	}

}
