package dev.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Component
public class Startup {

	private static final Logger LOG = LoggerFactory.getLogger(Startup.class);

	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public Startup(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.info("Démarrage de l'application");
		if (clientRepository.count() == 0) {

			Client client1 = new Client();
			Client client2 = new Client();
			client1.setNom("Pierre");
			client1.setPrenoms("Jean");
			client2.setNom("Albert");
			client2.setPrenoms("Dimitri");

			clientRepository.save(client1);
			clientRepository.save(client2);

		}
	}

}
