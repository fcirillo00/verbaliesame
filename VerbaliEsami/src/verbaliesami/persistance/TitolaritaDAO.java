package verbaliesami.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.Corso;
import verbaliesami.entity.Docente;
import verbaliesami.entity.Titolarita;

public class TitolaritaDAO {

	public static void create(int codice_corso, String matricola_docente, int anno_accademico) throws SQLException {
		
		PreparedStatement prep = null;
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("INSERT INTO TITOLARITA (codiceCorso, matricolaDocente, annoAccademico) VALUES (?,?,?)");
			
			prep.setInt(1, codice_corso);
			prep.setString(2, matricola_docente);
			prep.setInt(3, anno_accademico);
			
			prep.executeUpdate();
			
		} finally {
			
			if(prep != null) {
				prep.close();
			}
			
		}
	}
	
	public static ArrayList<Titolarita> readFromDocente(String matricolaDocente) throws SQLException{
		
		PreparedStatement prep = null;
		ArrayList<Titolarita> lista = new ArrayList<Titolarita>();
		
		try{
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM TITOLARITA WHERE matricolaDocente = ?");
			
			prep.setString(1, matricolaDocente);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int codiceCorso = rs.getInt("codiceCorso");
				int annoAccademico = rs.getInt("annoAccademico");
				
				lista.add(new Titolarita(matricolaDocente, codiceCorso, annoAccademico));
			}			
		}finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		return lista;
	}
	
	public static ArrayList<Titolarita> readFromCorso(int codiceCorso) throws SQLException{
		
		PreparedStatement prep = null;
		ArrayList<Titolarita> lista = new ArrayList<Titolarita>();
		
		try{
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM TITOLARITA WHERE codiceCorso = ?");
			
			prep.setInt(1, codiceCorso);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int annoAccademico = rs.getInt("annoAccademico");
				String matricolaDocente = rs.getString("matricolaDocente");
				
				lista.add(new Titolarita(matricolaDocente, codiceCorso, annoAccademico));
			}			
		}finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		return lista;
	}
	
	public static ArrayList<Titolarita> readFromCorsoDocente(int codiceCorso, String matricolaDocente) throws SQLException{
		
		PreparedStatement prep = null;
		ArrayList<Titolarita> lista = new ArrayList<Titolarita>();
		
		try{
			Connection conn = DBManager.getInstance().getConnection();
			
			prep = conn.prepareStatement("SELECT * FROM TITOLARITA WHERE codiceCorso = ? and matricolaDocente = ?");
			
			prep.setInt(1, codiceCorso);
			prep.setString(2, matricolaDocente);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()) {
				int annoAccademico = rs.getInt("annoAccademico");
				
				lista.add(new Titolarita(matricolaDocente, codiceCorso, annoAccademico));
			}			
		}finally {
			if(prep != null) {
				prep.close();
			}	
		}	
		return lista;
	}
	
//	public static ArrayList<Corso> readCorsiDocenteEver(String matricola_docente) throws SQLException{
//		
//		PreparedStatement prep = null;
//		ArrayList<Corso> lista = new ArrayList<Corso>();
//		
//		try{
//			Connection conn = DBManager.getInstance().getConnection();
//			
//			prep = conn.prepareStatement("SELECT codiceCorso FROM TITOLARITA WHERE matricolaDocente = ?");
//			
//			prep.setString(1, matricola_docente);
//			
//			ResultSet rs = prep.executeQuery();
//			
//			while(rs.next()) {
//				
//				int codice_corso = rs.getInt("codiceCorso");
//				
//				Corso c = CorsoDAO.read(codice_corso);			
//				
//				lista.add(c);
//			}			
//		}finally {
//			if(prep != null) {
//				prep.close();
//			}	
//		}	
//		return lista;
//	}
//	
//	
//	public static ArrayList<Docente> readDocentiFromCorso(int codiceCorso) throws SQLException{
//		
//		PreparedStatement prep = null;
//		ArrayList<Docente> lista = new ArrayList<Docente>();
//		
//		try{
//			Connection conn = DBManager.getInstance().getConnection();
//			
//			prep = conn.prepareStatement("SELECT d.nome, d.cognome, d.matricola FROM TITOLARITA as t, DOCENTE as d WHERE t.matricolaDocente = d.matricola and t.codiceCorso = ?");
//			
//			prep.setInt(1, codiceCorso);
//			
//			ResultSet rs = prep.executeQuery();
//			
//			while(rs.next()) {
//				
//				String nome = rs.getString("nome");
//				String cognome = rs.getString("cognome");
//				String matricola = rs.getString("matricola");
//				
//				Docente d = new Docente(nome, cognome, matricola, "****", "****");
//				lista.add(d);
//				
//			}			
//		}finally {
//			if(prep != null) {
//				prep.close();
//			}	
//		}	
//		return lista;
//	}
//	
//	public static ArrayList<Corso> readCorsiAttualiDocente(String matricola_docente, int annoAccademico) throws SQLException{
//		
//		PreparedStatement prep = null;
//		ArrayList<Corso> lista = new ArrayList<Corso>();
//		
//		try{
//			Connection conn = DBManager.getInstance().getConnection();
//			
//			prep = conn.prepareStatement("SELECT codiceCorso FROM TITOLARITA WHERE matricolaDocente = ? AND annoAccademico = ?");
//			
//			prep.setString(1, matricola_docente);
//			prep.setInt(2, annoAccademico);
//			
//			ResultSet rs = prep.executeQuery();
//			
//			while(rs.next()) {
//				
//				int codice_corso = rs.getInt("codiceCorso");
//				
//				Corso c = CorsoDAO.read(codice_corso);			
//				
//				lista.add(c);
//			}			
//		}finally {
//			if(prep != null) {
//				prep.close();
//			}	
//		}	
//		return lista;
//	}	
//	
	
	public static void delete_tit_corsi (int codice) throws SQLException {
		PreparedStatement prep = null;

		try {
			Connection conn = DBManager.getInstance().getConnection();
			prep = conn.prepareStatement("DELETE FROM TITOLARITA WHERE codiceCorso = ?");
			prep.setInt(1, codice);
			
			prep.executeUpdate();
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
	}
	
	public static void delete_tit_docenti (String matricola_docente) throws SQLException {
		PreparedStatement prep = null;

		try {
			Connection conn = DBManager.getInstance().getConnection();
			prep = conn.prepareStatement("DELETE FROM TITOLARITA WHERE matricolaDocente = ?");
			prep.setString(1, matricola_docente);
			
			prep.executeUpdate();
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
	}
	
	public static void delete(int codice_corso, String matricola_docente) throws SQLException {
		PreparedStatement prep = null;

		try {
			Connection conn = DBManager.getInstance().getConnection();
			prep = conn.prepareStatement("DELETE FROM TITOLARITA WHERE codiceCorso = ? AND matricolaDocente = ?");
			prep.setInt(1, codice_corso);
			prep.setString(2, matricola_docente);
			
			prep.executeUpdate();
		} finally {
			if(prep != null) {
				prep.close();
			}	
		}
	}
	
	
}
