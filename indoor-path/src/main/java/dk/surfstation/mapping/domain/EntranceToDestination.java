package dk.surfstation.mapping.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EntranceToDestination implements Serializable {
	@EmbeddedId
	private EntranceToDestinationId id;

	@ManyToOne
	@JoinColumn(name = "entrance_id", insertable = false, updatable = false)
	private Entrance entrance;

	@ManyToOne
	@JoinColumn(name = "destination_id", insertable = false, updatable = false)
	private Destination destination;
	private String pathUrl;

	@SuppressWarnings("unused")
	public EntranceToDestination()
	{
	}

	public EntranceToDestination(Entrance entrance, Destination destination, String pathUrl)
	{
		id = new EntranceToDestinationId(entrance.getId(), destination.getId());

		this.entrance = entrance;
		this.destination = destination;
		this.pathUrl = pathUrl;

		entrance.getEntranceToDestination().add(this);
		destination.getEntranceToDestination().add(this);
	}

	public Entrance getEntrance() {
		return entrance;
	}

	public void setEntrance(Entrance entrance) {
		this.entrance = entrance;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	@Embeddable
	public static class EntranceToDestinationId implements Serializable
	{
		@Column(name = "entrance_id")
		long entranceId;

		@Column(name = "destination_id")
		long destinationId;

		public EntranceToDestinationId() {
		}

		EntranceToDestinationId(long entranceId, long destinationId) {
			this.entranceId = entranceId;
			this.destinationId = destinationId;
		}

		@Override
		public boolean equals(Object o) {
			if(this == o) return true;
			if(o == null || getClass() != o.getClass()) return false;

			EntranceToDestinationId that = (EntranceToDestinationId) o;

			if(entranceId != that.entranceId) return false;
			return destinationId == that.destinationId;
		}

		@Override
		public int hashCode() {
			int result = (int) (entranceId ^ (entranceId >>> 32));
			result = 31 * result + (int) (destinationId ^ (destinationId >>> 32));
			return result;
		}
	}
}
