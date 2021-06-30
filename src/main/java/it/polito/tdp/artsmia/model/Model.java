package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private ArtsmiaDAO dao;
	private SimpleWeightedGraph<Artist , DefaultWeightedEdge> grafo;
	private Map<Integer, Artist> mapArtist;
	
	public Model(){
		dao = new ArtsmiaDAO();
		mapArtist = new HashMap<>();
		dao.listAllArtist(mapArtist);
	}
	
	public void creaGrafo(String role) {
		
		//Definisco grafo
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//Aggiungo i vertici
		Graphs.addAllVertices(this.grafo, dao.getArtistForRole(role));
		
		//Aggiungo gli archi
		for(LinkArtist l: dao.getLinkForArtist(mapArtist, role)) {
			Graphs.addEdgeWithVertices(this.grafo, l.getA1(), l.getA2(), l.getPeso());			
		}
	}
	
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
	
	
	public List<String> getRole() {
		return dao.listAllRoles();
	}

}
