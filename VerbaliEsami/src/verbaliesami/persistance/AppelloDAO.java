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

public class AppelloDAO {

	public static void create(int id, Calendar data, Calendar scadenza, String note, String sede, int codice_corso, String matricola_docente) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO APPELLO (id, data, scadenza, note, sede, codiceCorso, matricolaDocente) VALUES (?,?,?,?,?,?,?)");
			
			prep.setInt(1, id);
			prep.setDate(2, new java.sql.Date(data.getTimeInMillis()));
			prep.setDate(3, new java.sql.Date(scadenza.getTimeInMillis()));
			prep.setString(4, note);
			prep.setString(5, sede);
			prep.setInt(6, codice_corso);
			prep.setString(7, matricola_docente);
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
	}
	
	public static ArrayList<Appello> read(int codice_corso) throws SQLException{
		
		PreparedStatement prep = null;
		ArrayList<Appello> lista = new ArrayList<Appello>();
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM APPELLO WHERE codiceCorso = ?");
			prep.setInt(1, codice_corso);
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
				String matricola = rs.getString("matricolaDocente");
				Appello app = new Appello(data_c,scadenza_c,note,sede,codice,matricola);
				lista.add(app);
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		
		
		return lista;
	}
	
	public static int readId(Appello a) throws SQLException, NullPointerException {
		
		int id = 0;
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT id FROM APPELLO WHERE data = ? AND codiceCorso = ? AND matricolaDocente = ?");
			
			//Seleziono appello con stessa data, docente e corso, quindi è univoca
			prep.setDate(1, new java.sql.Date(a.getData().getTimeInMillis()));
			prep.setInt(2, a.getCorso().getCodice());
			prep.setString(3, a.getDocente().getMatricola());

			
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("id");
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}		
		
		return id;
			
	}
	
	public static Appello readAppello(int id_appello) throws SQLException{
		
		Appello app = null;
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM APPELLO WHERE id=?");
			
			prep.setInt(1,id_appello);
			
			ResultSet rs = prep.executeQuery();
			
			if (rs.next()) {
				Date data = rs.getDate("data");
				Calendar data_c = new GregorianCalendar();
				data_c.setTime(data);
				Date scadenza = rs.getDate("scadenza");
				Calendar scadenza_c = new GregorianCalendar();
				scadenza_c.setTime(scadenza);
				String note = rs.getString("note");
				String sede = rs.getString("sede");
				int codice = rs.getInt("codiceCorso");
				String matricola = rs.getString("matricolaDocente");
				app = new Appello(data_c,scadenza_c,note,sede,codice,matricola);
				
			}
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}		

		
		return app;
	}
	
	public static void delete(int id_appello) throws SQLException{
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("DELETE FROM APPELLO WHERE id=?");
			
			prep.setInt(1,id_appello);
			
			prep.executeUpdate();
			
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}		
		
	}
	
}
