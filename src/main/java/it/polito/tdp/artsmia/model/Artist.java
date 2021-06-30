package it.polito.tdp.artsmia.model;

public class Artist {

	private Integer idArtist;
	private String name;
	
	public Artist(Integer idArtist, String name) {
		super();
		this.idArtist = idArtist;
		this.name = name;
	}

	public Integer getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(Integer idArtist) {
		this.idArtist = idArtist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArtist == null) ? 0 : idArtist.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (idArtist == null) {
			if (other.idArtist != null)
				return false;
		} else if (!idArtist.equals(other.idArtist))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + idArtist + ") " + name + " |";
	}
	
	
	
	
}
