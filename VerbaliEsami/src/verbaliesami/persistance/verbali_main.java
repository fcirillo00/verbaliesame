package verbaliesami.persistance;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import verbaliesami.entity.Studente;
import verbaliesami.entity.Corso;
import verbaliesami.entity.Studente;

public class verbali_main {

	public static void main (String args[]) throws SQLException {
		System.out.println("Test funzionamento");
		
		Connection conn = DBManager.getInstance().getConnection();
		//Cambio
		
		//StudenteDAO.create("Aldo", "Baglio", "N46004951", "Ajeje", "Brazorf", 45620);
		
		StudenteDAO.create("Scemo", "Mongolo", "N46005050", "Attila", "Boh", 80809);
		
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
		
		System.out.println("------------------");
		
		StudenteDAO.delete("N46005050");
		
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
		
		
		System.out.println("-----------------------");
		
		String matricola = "N46004818";
		
		System.out.println("Leggo Studente con matricola " + matricola);
		Studente student = StudenteDAO.readSafe(matricola);
		student.mostraInfoStudente();
		System.out.println("--------------");
		
		//Corso c = CorsoDAO.create(4, "Sistemi operativi", 18);
		
		ArrayList<Corso> lista = CorsoDAO.read("Sistemi operativi");
		for (int i=0; i<lista.size(); i++) {
			lista.get(i).mostraCorso();
		}
		
	}
}
