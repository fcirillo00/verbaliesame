package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import verbaliesami.entity.Corso;

public class CorsoDAO {
	public static void create(int codice, String denominazione, int cfu) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO CORSO (codice, denominazione, cfu) VALUES (?,?,?)");
			
			prep.setInt(1, codice);
			prep.setString(2, denominazione);
			prep.setInt(3, cfu);

			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
	
	public static void create(Corso corso) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			int codice = corso.getCodice();
			int cfu = corso.getCfu();
			String denominazione = corso.getDenominazione();
			
			prep = conn.prepareStatement("INSERT INTO CORSO (codice, denominazione, cfu) VALUES (?,?,?)");
			
			prep.setInt(1, codice);
			prep.setString(2, denominazione);
			prep.setInt(3, cfu);

			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
	
	public static Corso read(int codice) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT denominazione, cfu FROM CORSO WHERE codice = ?");
			prep.setInt(1, codice);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				String denominazione = rs.getString("denominazione");
				int cfu = rs.getInt("cfu");
				
				return new Corso (codice, denominazione, cfu);
			}
			return null;
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}		
	}
	
	public static ArrayList<Corso> read(String denominazione) throws SQLException {
		
		PreparedStatement prep = null;
		ArrayList<Corso> lista = new ArrayList<Corso>();

		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT codice, cfu FROM CORSO WHERE denominazione = ?");
			prep.setString(1, denominazione);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				int codice = rs.getInt("codice");
				int cfu = rs.getInt("cfu");
				Corso c = new Corso(codice,denominazione,cfu);
				lista.add(c);
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		
		return lista;
	}
	
	public static ArrayList<Corso> readFromMatricola(String matricolaDocente) throws SQLException {
		
		PreparedStatement prep = null;
		ArrayList<Corso> lista = new ArrayList<Corso>();

		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT c.codice, c.denominazione, c.cfu FROM CORSO as c, TITOLARITA as t WHERE c.codice = t.codicecorso and t.matricolaDocente = ?");
			prep.setString(1, matricolaDocente);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				int codice = rs.getInt("codice");
				int cfu = rs.getInt("cfu");
				String denominazione = rs.getString("denominazione");
				Corso c = new Corso(codice,denominazione,cfu);
				lista.add(c);
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		
		return lista;
	}
	
	public static ArrayList<Corso> readFromNomeCognome(String nome, String cognome) throws SQLException {
		
		PreparedStatement prep = null;
		ArrayList<Corso> lista = new ArrayList<Corso>();

		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT c.codice, c.denominazione, c.cfu FROM CORSO as c, TITOLARITA as t, DOCENTE as d WHERE c.codice = t.codicecorso and t.matricolaDocente = d.matricola and d.nome = ? and d.cognome = ?");
			prep.setString(1, nome);
			prep.setString(2, cognome);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				int codice = rs.getInt("codice");
				int cfu = rs.getInt("cfu");
				String denominazione = rs.getString("denominazione");
				Corso c = new Corso(codice,denominazione,cfu);
				lista.add(c);
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		
		return lista;
	}

	public static void update (Corso c) throws SQLException {
		PreparedStatement prep = null;

		try {
			Connection conn = DBManager.getInstance().getConnection();
			prep = conn.prepareStatement("UPDATE CORSO SET denominazione = ?, cfu = ?, codice = ? WHERE codice = ?");
			prep.setString(1, c.getDenominazione());
			prep.setInt(2, c.getCfu());
			prep.setInt(3, c.getCodice());
			prep.setInt(4, c.getCodice());
			
			prep.executeUpdate();
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
	}
	
	public static void delete (int codice) throws SQLException {
		PreparedStatement prep = null;

		try {
			Connection conn = DBManager.getInstance().getConnection();
			prep = conn.prepareStatement("DELETE FROM CORSO WHERE codice = ?");
			prep.setInt(1, codice);
			
			prep.executeUpdate();
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
	}
}
