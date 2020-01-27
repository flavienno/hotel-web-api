package dev.hotel.entite;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Chambre extends BaseEntite {

	@NotEmpty
    private String numero;
	@NotEmpty
    private Float surfaceEnM2;
    
    @ManyToOne
    @NotNull
    private Hotel hotel;

    public Chambre() {
    }

    public Chambre(String numero, Float surfaceEnM2, Hotel hotel) {

        this.numero = numero;
        this.surfaceEnM2 = surfaceEnM2;
        this.hotel = hotel;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Float getSurfaceEnM2() {
        return surfaceEnM2;
    }

    public void setSurfaceEnM2(Float surfaceEnM2) {
        this.surfaceEnM2 = surfaceEnM2;
    }
}
