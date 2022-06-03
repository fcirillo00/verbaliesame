package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.Appello;
import verbaliesami.entity.Docente;

public class DocenteDAO {

	
	public static void create(String nome, String cognome, String matricola, String username, String password) throws SQLException{
		
		PreparedStatement p = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			p = conn.prepareStatement("INSERT INTO DOCENTE (matricola, cognome, nome, username, password) VALUES (?, ?, ?, ?, ?)");
			p.setString(1, matricola);
			p.setString(2, cognome);
			p.setString(3, nome);
			p.setString(4, username);
			p.setString(5, password);
			p.executeUpdate();
			
		} finally {
			if (p != null) {
				p.close();
			}
		}
		
		
	}
	
	
	public static void create(Docente d) throws SQLException {
		
		PreparedStatement p = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			p = conn.prepareStatement("INSERT INTO DOCENTE (matricola, cognome, nome, username, password) VALUES (?, ?, ?, ?, ?)");
			p.setString(1, d.getMatricola());
			p.setString(2, d.getCognome());
			p.setString(3, d.getNome());
			p.setString(4, d.getUsername());
			p.setString(5, d.getPassword());
			p.executeUpdate();
		
		} finally {
			if (p != null) {
				p.close();
			}
		}
		
	}
	
	
	public static void delete(String matricola) throws SQLException{
		
		PreparedStatement p = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			p = conn.prepareStatement("DELETE FROM DOCENTE WHERE matricola=?");
			p.setString(1, matricola);
			p.executeUpdate();
			
		} finally {
			if (p != null) {
				p.close();
			}
		}
	}
	
	
	public static Docente readSafe(String matricola) throws SQLException{
		
		PreparedStatement p = null;
		
		try {
		Connection conn = DBManager.getInstance().getConnection();
		p = conn.prepareStatement("SELECT matricola, nome, cognome, username FROM DOCENTE WHERE matricola=?");
		p.setString(1, matricola);
		ResultSet rs = p.executeQuery();
		Docente d = null;
		
		if (rs.next()) {
			String m = rs.getString("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String u = rs.getString("username");
			d = new Docente (n, c, m, u, "****");
			
		}
		return d;
		
		} finally {
			if (p != null) {
				p.close();
			}
		}
	}
		

		public static Docente readUnsafe(String matricola) throws SQLException{
			
			PreparedStatement p = null;
			
			try {
			Connection conn = DBManager.getInstance().getConnection();
			p = conn.prepareStatement("SELECT matricola, nome, cognome, username, password FROM DOCENTE WHERE matricola=?");
			p.setString(1, matricola);
			ResultSet rs = p.executeQuery();
			Docente d = null;
			
			if (rs.next()) {
				String m = rs.getString("matricola");
				String n = rs.getString("nome");
				String c = rs.getString("cognome");
				String u = rs.getString("username");
				String pw = rs.getString("password");
				d = new Docente (n, c, m, u, pw);
				
			}
			return d;
			
			} finally {
				if (p != null) {
					p.close();
				}
			}		
				
	}
		
	public static ArrayList<Docente> readSafe(String nome, String cognome) throws SQLException{
		
		PreparedStatement p = null;
		ArrayList<Docente> lista = new ArrayList<Docente>();
		
		try {
		Connection conn = DBManager.getInstance().getConnection();
		p = conn.prepareStatement("SELECT matricola, nome, cognome, username FROM DOCENTE WHERE nome=? AND cognome=?");
		p.setString(1, nome);
		p.setString(2, cognome);
		ResultSet rs = p.executeQuery();
		Docente d = null;
		
		if (rs.next()) {
			String m = rs.getString("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String u = rs.getString("username");
			d = new Docente (n, c, m, u, "****");
			lista.add(d);
		}
		
		} finally {
			if (p != null) {
				p.close();
			}
		}
		return lista;
	}
	
	public static ArrayList<Docente> readUnsafe(String nome, String cognome) throws SQLException{
		
		PreparedStatement p = null;
		ArrayList<Docente> lista = new ArrayList<Docente>();
		
		try {
		Connection conn = DBManager.getInstance().getConnection();
		p = conn.prepareStatement("SELECT matricola, nome, cognome, username, password FROM DOCENTE WHERE nome=? AND cognome=?");
		p.setString(1, nome);
		p.setString(2, cognome);
		ResultSet rs = p.executeQuery();
		Docente d = null;
		
		if (rs.next()) {
			String m = rs.getString("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String u = rs.getString("username");
			String pw = rs.getString("password");
			d = new Docente (n, c, m, u, pw);
			lista.add(d);
		}
		
		} finally {
			if (p != null) {
				p.close();
			}
		}
		return lista;
	}
	
	
	
}
