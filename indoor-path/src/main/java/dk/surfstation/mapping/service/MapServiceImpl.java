package dk.surfstation.mapping.service;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.repository.DestinationRepository;
import dk.surfstation.mapping.repository.EntranceRepository;
import dk.surfstation.mapping.repository.EntranceToDestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
	private final EntranceRepository entranceRepository;
	private final DestinationRepository destinationRepository;
	private final EntranceToDestinationRepository entranceToDestinationRepository;

	public MapServiceImpl(EntranceRepository entranceRepository, DestinationRepository destinationRepository, EntranceToDestinationRepository entranceToDestinationRepository) {
		this.entranceRepository = entranceRepository;
		this.destinationRepository = destinationRepository;
		this.entranceToDestinationRepository = entranceToDestinationRepository;
	}

	@Override
	public List<Destination> getDestinations() {
		return destinationRepository.findAll();
	}

	@Override
	public String getPathUrl(long entranceId, long destinationId) {
		Entrance entrance = entranceRepository.findOne(entranceId);
		Destination destination = destinationRepository.findOne(destinationId);
		return entranceToDestinationRepository.getPathByEntranceAndDestination(entrance, destination).getPathUrl();
	}

	@Override
	public List<Entrance> getEntrances() {
		return entranceRepository.findAll();
	}
}
