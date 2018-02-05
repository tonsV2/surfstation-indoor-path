package dk.surfstation.mapping.service;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.domain.EntranceToDestination;

import java.util.List;

public interface MapService {
	List<Destination> getDestinations();
	String getPathUrl(long entranceId, long destinationId);
	List<EntranceToDestination> findOne(long entranceId, long destinationId);
	List<Entrance> getEntrances();
}
