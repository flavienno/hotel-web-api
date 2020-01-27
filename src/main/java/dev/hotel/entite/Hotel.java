package dev.hotel.entite;


import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Hotel extends BaseEntite {

	@NotEmpty
    private String nom;
	
	@NotNull
    private Integer nombreEtoiles;

    public Hotel() {
    }

    public Hotel(String nom, Integer nombreEtoiles) {
        this.nom = nom;
        this.nombreEtoiles = nombreEtoiles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNombreEtoiles() {
        return nombreEtoiles;
    }

    public void setNombreEtoiles(Integer nombreEtoiles) {
        this.nombreEtoiles = nombreEtoiles;
    }
}
