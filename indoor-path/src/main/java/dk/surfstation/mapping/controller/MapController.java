package dk.surfstation.mapping.controller;

import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.service.MapService;
import dk.surfstation.mapping.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
	private final StorageService storageService;
	private final MapService mapService;
	private final String LOGO_TAG = "logo";

	public MapController(StorageService storageService, MapService mapService) {
		this.storageService = storageService;
		this.mapService = mapService;
	}
/*
	@GetMapping("/logos")
	public Stream<Path> getAllLogos() throws IOException {
		return storageService.loadAll(LOGO_TAG);
	}

	@PostMapping("/upload/logo")
	public ResponseEntity<?> uploadLogo(MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.ok("Please select a file!");
		}
		try {
			storageService.store(LOGO_TAG, file);
		} catch (IOException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok("Successfully uploaded - " + file.getOriginalFilename());
	}

	@GetMapping("/logo/{filename:.+}")
	public ResponseEntity<Resource> getLogo(@PathVariable String filename) throws MalformedURLException {
		Path file = storageService.load(LOGO_TAG, filename);
		Resource resource = new UrlResource(file.toUri());
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
*/
	@GetMapping("/entrances/{entranceId}/destination/{destinationId}")
	public ResponseEntity<?> getPath(@PathVariable long entranceId, @PathVariable long destinationId) {
		String pathUrl = mapService.getPathUrl(entranceId, destinationId);
		if (pathUrl == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pathUrl);
	}

	@GetMapping("/entrances")
	public List<Entrance> getEntrances() {
		return mapService.getEntrances();
	}
}
