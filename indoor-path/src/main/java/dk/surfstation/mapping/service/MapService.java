package dk.surfstation.mapping.service;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;

import java.util.List;

public interface MapService {
	List<Destination> getDestinations();
	String getPathUrl(long entranceId, long destinationId);
	List<Entrance> getEntrances();
}
