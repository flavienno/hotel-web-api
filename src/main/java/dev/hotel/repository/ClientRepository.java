package dev.hotel.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	// equivaut à select * from client where nom=?
	List<Client> findByNom(String nom);

	boolean existsByNomAndPrenoms(String nom, String prenoms);

	Optional<Client> findByNomAndPrenoms(String nom, String prenoms);

	Optional<Client> findByPrenoms(String prenoms);

}
