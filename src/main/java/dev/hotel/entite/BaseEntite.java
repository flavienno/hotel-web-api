package dev.hotel.entite;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseEntite {

	@Id
	@Type(type="uuid-char")
	@NotNull
    private UUID uuid;

    public BaseEntite() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
