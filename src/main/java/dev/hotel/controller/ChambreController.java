package dev.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;


@RestController
public class ChambreController {

	private ChambreRepository chambreRepository;

	/**
	 * @param chambreRepository
	 */
	public ChambreController(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/chambres")
	public List<Chambre>retourChambres(){
		
		List<Chambre> listChambres=chambreRepository.findAll();
		return listChambres;
	}

}
