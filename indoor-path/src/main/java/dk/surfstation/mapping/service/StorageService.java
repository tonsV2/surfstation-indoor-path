package dk.surfstation.mapping.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	URL store(MultipartFile file) throws IOException;
	void delete(String filename);
}
