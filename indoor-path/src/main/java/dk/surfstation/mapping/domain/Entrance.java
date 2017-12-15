package dk.surfstation.mapping.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Entrance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "entrance", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EntranceToDestination> entranceToDestination = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<EntranceToDestination> getEntranceToDestination() {
		return entranceToDestination;
	}

	public void setEntranceToDestination(Set<EntranceToDestination> entranceToDestination) {
		this.entranceToDestination = entranceToDestination;
	}
}
