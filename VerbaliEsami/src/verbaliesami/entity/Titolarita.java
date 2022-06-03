package verbaliesami.entity;

import java.sql.SQLException;

import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;

public class Titolarita {
	
	private int anno_accademico;
	private Docente docente;
	private Corso corso;
	
	public Titolarita(String matricola_docente, int codice_corso, int anno_accademico) {
		this.anno_accademico = anno_accademico;
		
		try {
			this.docente = DocenteDAO.readSafe(matricola_docente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Docente non trovato");
			return;
		}
		
		try {
			this.corso = CorsoDAO.read(codice_corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Corso non trovato");
			return;
		}
		
	}
	
	
	public int getAnno_accademico() {
		return anno_accademico;
	}
	public void setAnno_accademico(int anno_accademico) {
		this.anno_accademico = anno_accademico;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = new Docente(docente);
	}
	public void setDocente(String matricola_docente) {
		try {
			this.docente = DocenteDAO.readSafe(matricola_docente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Docente non trovato");
			return;
		}
	}
	public Corso getCorso() {
		return corso;
	}
	public void setCorso(Corso corso) {
		this.corso = new Corso(corso);
	}
	public void setCorso(int codice_corso) {
		
		try {
			this.corso = CorsoDAO.read(codice_corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Corso non trovato");
			return;
		}		
	}
	

}
