package dk.surfstation.mapping.repository;

import dk.surfstation.mapping.domain.Destination;
import dk.surfstation.mapping.domain.Entrance;
import dk.surfstation.mapping.domain.EntranceToDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntranceToDestinationRepository extends JpaRepository<EntranceToDestination, Long> {
	@Query("select ed.pathUrl from EntranceToDestination ed where ed.entrance = :entrance and ed.destination = :destination")
	String getPath(@Param("entrance") Entrance entrance, @Param("destination") Destination destination);
}
