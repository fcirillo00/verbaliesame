package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.entity.Corso;
import verbaliesami.entity.Appello;
import verbaliesami.entity.Appello.Sede;

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
	
}
