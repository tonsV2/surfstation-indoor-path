package dk.surfstation.mapping.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private URL url;
	@JsonIgnore
	@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
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

	public URL getUrl()
	{
		return url;
	}

	public void setUrl(URL url)
	{
		this.url = url;
	}

	public Set<EntranceToDestination> getEntranceToDestination() {
		return entranceToDestination;
	}

	public void setEntranceToDestination(Set<EntranceToDestination> entranceToDestination) {
		this.entranceToDestination = entranceToDestination;
	}
}
