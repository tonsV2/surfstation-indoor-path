package dk.surfstation.mapping.repository;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
