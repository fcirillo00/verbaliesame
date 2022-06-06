package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.entity.Appello;
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
	
	public static ArrayList<Appello> readAppelli(String matricola) throws SQLException {
		ArrayList<Appello> appelli = new ArrayList<Appello>();
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT a.id, a.data, a.scadenza, a.note, a.sede, a.codiceCorso, a.matricolaStudente FROM PRENOTAZIONE as p, APPELLO as a WHERE p.matricolaStudente = ? and p.appello = a.id");
			
			prep.setString(1, matricola);
			
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				Date data = rs.getDate("data");
				Calendar data_c = new GregorianCalendar();
				data_c.setTime(data);
				Date scadenza = rs.getDate("scadenza");
				Calendar scadenza_c = new GregorianCalendar();
				scadenza_c.setTime(scadenza);
				String note = rs.getString("note");
				String sede = rs.getString("sede");
				int codice = rs.getInt("codiceCorso");
				String matricola_doc = rs.getString("matricolaDocente");
				Appello app = new Appello(data_c,scadenza_c,note,sede,codice,matricola_doc);
				appelli.add(app);
			}
		} finally {
			if(prep != null) {
				prep.close();
			}
		}
		return appelli;
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
