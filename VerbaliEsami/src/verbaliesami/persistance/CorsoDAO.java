package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import verbaliesami.entity.Corso;

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
}
