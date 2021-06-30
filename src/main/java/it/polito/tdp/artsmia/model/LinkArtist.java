package it.polito.tdp.artsmia.model;

public class LinkArtist implements Comparable<LinkArtist> {
	
	private Artist a1;
	private Artist a2;
	private Integer peso;
	
	public LinkArtist(Artist a1, Artist a2, Integer peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}

	public Artist getA1() {
		return a1;
	}

	public void setA1(Artist a1) {
		this.a1 = a1;
	}

	public Artist getA2() {
		return a2;
	}

	public void setA2(Artist a2) {
		this.a2 = a2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}


	@Override
	public int compareTo(LinkArtist o) {
		return -(this.peso.compareTo(o.peso));
	}

	@Override
	public String toString() {
		return a1 + " " + a2 + " peso=" + peso + ";";
	}
	
	
	
	

}
