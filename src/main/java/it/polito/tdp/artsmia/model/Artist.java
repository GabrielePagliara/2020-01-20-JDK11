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
	
	
	
	
}
