package dev.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {

	//List<Chambre> findBynomDeChambre(String nom);
}
