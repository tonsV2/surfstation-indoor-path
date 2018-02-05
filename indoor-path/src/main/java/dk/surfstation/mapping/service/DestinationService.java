package dk.surfstation.mapping.service;

import dk.surfstation.mapping.domain.Destination;

import java.util.List;

public interface DestinationService {
	Destination save(Destination destination);
	List<Destination> findAll();
	Destination findOne(long id);
}
