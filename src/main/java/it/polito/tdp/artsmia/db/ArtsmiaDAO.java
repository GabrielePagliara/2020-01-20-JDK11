package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Artist;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.LinkArtist;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Exhibition> listExhibitions() {
		
		String sql = "SELECT * from exhibitions";
		List<Exhibition> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Exhibition exObj = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), 
						res.getInt("begin"), res.getInt("end"));
				
				result.add(exObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> listAllRoles() {
		String sql = "SELECT DISTINCT role FROM authorship ORDER BY role";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("role"));
			}
			conn.close();
			return result;		
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Artist> getArtistForRole(String role) {
		
		String sql = "SELECT a.artist_id as id, a.name as nome "
				+ "FROM artists a, authorship au "
				+ "WHERE a.artist_id = au.artist_id AND au.role = ? "
				+ "GROUP BY a.artist_id ";
		List<Artist> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, role);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Artist artist = new Artist(res.getInt("id"), res.getString("nome"));
				result.add(artist);
			}
			conn.close();
			return result;				
		}catch(SQLException e) {
			System.out.println("Errore in ruolo dell'artista");
			e.printStackTrace();
			return null;
		}
	}

	public List<LinkArtist> getLinkForArtist(Map<Integer, Artist> mapArtist, String role) {
		String sql = "SELECT a1.artist_id AS a1, a2.artist_id AS a2, COUNT(eo1.exhibition_id) AS peso "
				+ "FROM authorship a1, authorship a2, exhibition_objects eo1, exhibition_objects eo2 "
				+ "WHERE a1.artist_id > a2.artist_id AND a1.object_id = eo1.object_id AND "
				+ "a1.role = \"Designer\" AND a2.role= ? AND a2.object_id = eo2.object_id AND eo1.exhibition_id = eo2.exhibition_id "
				+ "GROUP BY a1.artist_id, a2.artist_id ";
		List<LinkArtist> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, role);
			ResultSet res = st.executeQuery();
					
			while (res.next()) {
				Artist a1 = mapArtist.get(res.getInt("a1"));
				Artist a2 = mapArtist.get(res.getInt("a2"));	
				LinkArtist linkartist = new LinkArtist(a1 , a2 ,res.getInt("peso"));
				result.add(linkartist);
			}
			conn.close();
			return result;				
			
		}catch(SQLException e) {
			System.out.println("Errore link artist");
			e.printStackTrace();
			return null;
		}
	}

	public void listAllArtist(Map<Integer, Artist> mapArtist) {
		String sql = "SELECT * FROM artists ";
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if (!mapArtist.containsKey("artist_id")) {
					Artist artist = new Artist(res.getInt("artist_id"), res.getString("name"));
					mapArtist.put(artist.getIdArtist(), artist);
				}
			}
			conn.close();

		} catch (SQLException e) {
			System.out.println("Errore caricamento mappa");
			e.printStackTrace();
		}
		
		
	}
	
}
