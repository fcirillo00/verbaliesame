package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.Valutazione;
import verbaliesami.entity.Verbale;

public class ValutazioneDAO {

	public static void create(int voto, String argomenti, int verbale, String matricola_studente) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO VALUTAZIONE (voto, argomenti_trattati, verbale, matricolaStudente) VALUES (?,?,?,?)");
			
			prep.setInt(1, voto);
			prep.setString(2, argomenti);
			prep.setInt(3, verbale);
			prep.setString(4, matricola_studente);

			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
	
	public static Valutazione read(int verbale, String matricola_studente) throws SQLException{
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT (voto, argomenti_trattati) FROM VALUTAZIONE WHERE verbale = ? AND matricolaStudente=?");
			
			prep.setInt(1, verbale);
			prep.setString(2, matricola_studente);
			
			ResultSet rs = prep.executeQuery();
			
			Valutazione v = null;
			
			if(rs.next()) {
				int voto = rs.getInt("voto");
				String argomenti = rs.getString("argomenti_trattati");
				Verbale verbale_riferito = new Verbale(VerbaleDAO.read(verbale));
				
				v = new Valutazione(voto, argomenti, verbale_riferito, matricola_studente);
			}

			return v;
		}finally {
			if(prep != null) {
				prep.close();
			}
		}
	}
	
	public static ArrayList<Valutazione> readValutazioni(int verbale) throws SQLException {
		
		PreparedStatement prep = null;
		ArrayList<Valutazione> lista = new ArrayList<Valutazione>();
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM VALUTAZIONE WHERE verbale = ?");
			prep.setInt(1, verbale);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int voto = rs.getInt("voto");
				String argomenti = rs.getString("argomenti_trattati");
				String mat_studente = rs.getString("matricolaStudente");
				Verbale verbale_riferito = new Verbale(VerbaleDAO.read(verbale));
				
				lista.add(new Valutazione(voto, argomenti, verbale_riferito, mat_studente));
			}			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
		
		return lista;
		
	}

public static ArrayList<Valutazione> readValutazioniStudente(String matricola) throws SQLException {
		
		PreparedStatement prep = null;
		ArrayList<Valutazione> lista = new ArrayList<Valutazione>();
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM VALUTAZIONE WHERE matricolaStudente = ?");
			prep.setString(1, matricola);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int voto = rs.getInt("voto");
				String argomenti = rs.getString("argomenti_trattati");
				String mat_studente = rs.getString("matricolaStudente");
				int verbale = rs.getInt("verbale");
				Verbale verbale_riferito = new Verbale(VerbaleDAO.read(verbale));
				
				lista.add(new Valutazione(voto, argomenti, verbale_riferito, mat_studente));
			}			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
		
		return lista;
		
	}
	
}
