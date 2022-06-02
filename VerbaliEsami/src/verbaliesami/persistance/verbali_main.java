package verbaliesami.persistance;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import verbaliesami.entity.Studente;
import verbaliesami.entity.Corso;

public class verbali_main {

	public static void main (String args[]) throws SQLException {
		System.out.println("Test funzionamento");
		
		Connection conn = DBManager.getInstance().getConnection();
		//Cambio
		
		//Studente studente = StudenteDAO.create("Aldo", "Baglio", "N46004951", "Ajeje", "Brazorf", 45620);
		
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
		
		//Corso c = CorsoDAO.create(4, "Sistemi operativi", 18);
		
		ArrayList<Corso> lista = CorsoDAO.read("Sistemi operativi");
		for (int i=0; i<lista.size(); i++) {
			lista.get(i).mostraCorso();
		}
	}
}
