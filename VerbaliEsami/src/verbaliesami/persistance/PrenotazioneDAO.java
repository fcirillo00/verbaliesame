package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.Studente;

public class PrenotazioneDAO {
	
	public static void create(String matricolaStudente, int appello) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO PRENOTAZIONE (matricolaStudente, appello) VALUES (?,?)");
			
			prep.setString(1, matricolaStudente);
			prep.setInt(2, appello);

			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
	
	public static ArrayList<Studente> readStudenti(int appello) throws SQLException {
		ArrayList<Studente> studenti = new ArrayList<Studente>();
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT s.matricola, s.cognome, s.nome FROM PRENOTAZIONE as p, STUDENTE as s WHERE p.matricolaStudente = s.matricola and p.appello = ?");
			
			prep.setInt(1, appello);
			
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				
				Studente stud = new Studente(nome,cognome,matricola, "****", "****", 1);
				studenti.add(stud);
			}
		} finally {
			if(prep != null) {
				prep.close();
			}
		}
		return studenti;
	}
	
	public static void delete(String matricolaStudente, int appello) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("DELETE FROM PRENOTAZIONE where matricolaStudente = ? and appello = ?");
			
			prep.setString(1, matricolaStudente);
			prep.setInt(2, appello);

			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
}
