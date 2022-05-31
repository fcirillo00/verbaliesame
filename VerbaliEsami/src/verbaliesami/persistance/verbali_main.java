package verbaliesami.persistance;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class verbali_main {

	public static void main (String args[]) throws SQLException {
		System.out.println("Test funzionamento");
		Connection conn = DBManager.getInstance().getConnection();
		
		Statement s = null;
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM STUDENTE");
			while (rs.next()) {
				String matricola = rs.getString("matricola");
				System.out.println("SQL: " + matricola);
			}
		} finally {
			if (s != null) { s.close(); }
		}
	}
}
