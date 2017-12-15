package dk.surfstation.mapping.util;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.domain.EntranceToDestination;
import dk.surfstation.mapping.repository.DestinationRepository;
import dk.surfstation.mapping.repository.EntranceRepository;
import dk.surfstation.mapping.repository.EntranceToDestinationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Initialize implements ApplicationRunner {
	private final EntranceRepository entranceRepository;
	private final DestinationRepository destinationRepository;
	private final EntranceToDestinationRepository entranceToDestinationRepository;

	public Initialize(EntranceRepository entranceRepository, DestinationRepository destinationRepository, EntranceToDestinationRepository entranceToDestinationRepository) {
		this.entranceRepository = entranceRepository;
		this.destinationRepository = destinationRepository;
		this.entranceToDestinationRepository = entranceToDestinationRepository;
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		Entrance entranceA = new Entrance();
		entranceA.setName("A");
		entranceRepository.save(entranceA);

		Entrance entranceB = new Entrance();
		entranceB.setName("B");
		entranceRepository.save(entranceB);

		Entrance entranceC = new Entrance();
		entranceC.setName("C");
		entranceRepository.save(entranceC);

		Entrance entranceD = new Entrance();
		entranceD.setName("D");
		entranceRepository.save(entranceD);

		Destination destination0 = new Destination();
		destination0.setName("Google");
		destinationRepository.save(destination0);

		Destination destination1 = new Destination();
		destination1.setName("Microsoft");
		destinationRepository.save(destination1);

		Destination destination2 = new Destination();
		destination2.setName("Surfstation");
		destinationRepository.save(destination2);

		String host = "localhost:8080";
		String format = host + "/entrances/%s/destination/%s" + System.lineSeparator();

		EntranceToDestination entranceToDestination = new EntranceToDestination(entranceA, destination0, "some url for A to Google");
		entranceToDestinationRepository.save(entranceToDestination);
		System.out.printf(format, entranceA.getId(), destination0.getId());

		EntranceToDestination AToMS = new EntranceToDestination(entranceA, destination1, "some url for A to Microsoft");
		entranceToDestinationRepository.save(AToMS);
		System.out.printf(format, entranceA.getId(), destination1.getId());

		EntranceToDestination BToGoogle = new EntranceToDestination(entranceB, destination1, "some url for B to Google");
		entranceToDestinationRepository.save(BToGoogle);
		System.out.printf(format, entranceB.getId(), destination1.getId());
	}
}
