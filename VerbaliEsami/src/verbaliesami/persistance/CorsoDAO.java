package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import verbaliesami.entity.Corso;

// TODO: altri read

public class CorsoDAO {
	public static Corso create(int codice, String denominazione, int cfu) throws SQLException {
		Corso corso = new Corso(codice, denominazione, cfu);
		
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
		
		return corso;
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
	

}
