package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	// select * from client where nom=?
	Client[] findByNom(String nom);

	boolean existsByNomAndPrenoms(String nom, String prenoms);
	
	Optional<Client> findByPrenoms(String prenoms);
	

}
