package dk.surfstation.mapping.service;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
	private final DestinationRepository destinationRepository;

	public DestinationServiceImpl(DestinationRepository destinationRepository) {
		this.destinationRepository = destinationRepository;
	}

	@Override
	public Destination save(Destination destination) {
		return destinationRepository.save(destination);
	}

	@Override
	public List<Destination> findAll() {
		return destinationRepository.findAll();
	}

	@Override
	public Destination findOne(long id) {
		return destinationRepository.findOne(id);
	}
}
