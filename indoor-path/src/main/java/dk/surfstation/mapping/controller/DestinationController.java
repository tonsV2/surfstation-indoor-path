package dk.surfstation.mapping.controller;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.EntranceToDestination;
import dk.surfstation.mapping.repository.EntranceToDestinationRepository;
import dk.surfstation.mapping.service.DestinationService;
import dk.surfstation.mapping.service.ObjectExistsException;
import dk.surfstation.mapping.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class DestinationController {
	private final DestinationService destinationService;
	private final StorageService storageService;
	private final EntranceToDestinationRepository entranceToDestinationRepository;

	public DestinationController(DestinationService destinationService, StorageService storageService, EntranceToDestinationRepository entranceToDestinationRepository) {
		this.destinationService = destinationService;
		this.storageService = storageService;
		this.entranceToDestinationRepository = entranceToDestinationRepository;
	}

	@GetMapping("/destinations/{id}/entrances")
	public List<EntranceToDestination> getEntrances(@PathVariable long id) {
		return entranceToDestinationRepository.findOne(id);
	}

	@RequestMapping(value = "/destinations/{id}/logo", method = {POST, PUT})
	public ResponseEntity<?> save(@PathVariable long id, @RequestBody MultipartFile file) throws IOException {
		Destination destination = destinationService.findOne(id);
		if (destination == null) {
			return ResponseEntity.notFound().build();
		}
		URL url = null;
		try {
			url = storageService.store(file);
		} catch(ObjectExistsException e) {
			e.printStackTrace();
		}
		destination.setUrl(url);
		return ResponseEntity.ok(destinationService.save(destination));
	}

	@DeleteMapping(value = "/destinations/{id}/logo")
	public ResponseEntity<?> deleteLogo(@PathVariable long id) {
		Destination destination = destinationService.findOne(id);
		URL url = destination.getUrl();
		String path = url.getPath();
		storageService.delete(path);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/destinations", method = {POST, PUT})
	public Destination save(@RequestBody Destination destination) {
		return destinationService.save(destination);
	}

	@GetMapping("/destinations")
	public List<Destination> getDestinations() {
		return destinationService.findAll();
	}
}
