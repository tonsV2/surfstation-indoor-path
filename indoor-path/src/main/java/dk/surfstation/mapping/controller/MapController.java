package dk.surfstation.mapping.controller;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
	private final MapService mapService;

	public MapController(MapService mapService) {
		this.mapService = mapService;
	}

	@GetMapping("/entrances/{entranceId}/destination/{destinationId}")
	public ResponseEntity<?> getPath(@PathVariable long entranceId, @PathVariable long destinationId) {
		String pathUrl = mapService.getPathUrl(entranceId, destinationId);
		if (pathUrl == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pathUrl);
	}

	@GetMapping("/destinations")
	public List<Destination> getDestinations() {
		return mapService.getDestinations();
	}

	@GetMapping("/entrances")
	public List<Entrance> getEntrances() {
		return mapService.getEntrances();
	}
}
