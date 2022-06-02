package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import verbaliesami.entity.Studente;

public class StudenteDAO {

	public static void create(String nome, String cognome, String matricola,String username, String password, int pin) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO STUDENTE (matricola, cognome, nome, username, password, pin) VALUES (?,?,?,?,?,?)");
			
			prep.setString(1, matricola);
			prep.setString(2, cognome);
			prep.setString(3, nome);
			prep.setString(4, username);
			prep.setString(5, password);
			prep.setInt(6, pin);
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
	}
	
	public static void create(Studente s) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO STUDENTE (matricola, cognome, nome, username, password, pin) VALUES (?,?,?,?,?,?)");
			
			prep.setString(1, s.getMatricola());
			prep.setString(2, s.getCognome());
			prep.setString(3, s.getNome());
			prep.setString(4, s.getUsername());
			prep.setString(5, s.getPassword());
			prep.setInt(6, s.getPin());
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
	}
	
	public static void delete(String matricola) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("DELETE FROM STUDENTE WHERE matricola=?");
			
			prep.setString(1, matricola);
			
			prep.executeUpdate();
			
		} finally {
			if(prep != null) {
				prep.close();
			}
		}
		
	}
	
	public static Studente readUnsafe(String matricola) throws SQLException{
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM STUDENTE WHERE matricola=?");
			
			prep.setString(1, matricola);
			
			ResultSet rs = prep.executeQuery();
			
			Studente s = null;
			
			if(rs.next()) {
				String matr = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int pin = rs.getInt("pin");
				
				s = new Studente(nome, cognome, matr, username, password, pin);
			}

			return s;
		}finally {
			if(prep != null) {
				prep.close();
			}
		}
	}
	
public static Studente readSafe(String matricola) throws SQLException{
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT matricola,cognome,nome FROM STUDENTE WHERE matricola=?");
			
			prep.setString(1, matricola);
			
			ResultSet rs = prep.executeQuery();
			
			Studente s = null;
			
			if(rs.next()) {
				String matr = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				
				s = new Studente(nome, cognome, matr, "****", "****", -1);
			}

			return s;
		}finally {
			if(prep != null) {
				prep.close();
			}
		}
	}
	
}
