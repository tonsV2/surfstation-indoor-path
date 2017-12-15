package dk.surfstation.mapping.repository;

import dk.surfstation.mapping.domain.Entrance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceRepository extends JpaRepository<Entrance, Long> {
}
