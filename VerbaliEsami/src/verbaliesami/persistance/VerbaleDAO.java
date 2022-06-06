package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import verbaliesami.entity.Appello;
import verbaliesami.entity.Verbale;


public class VerbaleDAO {

	public static void create(int id_verbale, Appello appello_riferito) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO VERBALE (id, appello) VALUES (?,?)");
			
			prep.setInt(1, id_verbale);
			prep.setInt(2, AppelloDAO.readId(appello_riferito));
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
	}
	
	public static Verbale read(int id_verbale) throws SQLException {
		
		PreparedStatement prep = null;
		Verbale v = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM VERBALE WHERE id=?");
			
			prep.setInt(1, id_verbale);
			
			ResultSet rs = prep.executeQuery();
			
			if(rs.next()) {
				int id_appello = rs.getInt("appello");
				
				Appello app = AppelloDAO.readAppello(id_appello);
				
				v = new Verbale(app);				
			}
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
		return v;
	}
	
	public static int readIdFromAppello(int appello) throws SQLException{
		PreparedStatement prep = null;
		int id_verbale = 0;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM VERBALE WHERE appello=?");
			
			prep.setInt(1, appello);
			
			ResultSet rs = prep.executeQuery();
			
			if(rs.next()) {
				id_verbale = rs.getInt("id");				
			}
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
		
		return id_verbale;
	}
	
	
}
