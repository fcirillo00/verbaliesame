package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import verbaliesami.entity.Studente;

public class StudenteDAO {

	public static Studente create(String nome, String cognome, String matricola,String username, String password, int pin) throws SQLException {
		
		Studente s = new Studente(nome, cognome, matricola,username, password, pin);
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO STUDENTE (matricola, cognome, nome, username, password, pin) VALUES (?,?,?,?,?,?)");
			
			prep.setString(1, matricola);
			prep.setString(2, cognome);
			prep.setString(3, nome);
			prep.setString(4, username);
			prep.setString(5, password);
			prep.setInt(6, pin);
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}

		return s;
	}
	
}
