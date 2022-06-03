package verbaliesami.control;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.*;

public class VerbaliManagementSystem {

	private static VerbaliManagementSystem instance = null;
	
	protected VerbaliManagementSystem() {
	}

	public static VerbaliManagementSystem getInstance() throws SQLException {
		if (instance == null) {
			instance = new VerbaliManagementSystem(); 
		}
		return instance;
	}
	
	public void agg_docente(String matricola, String nome_docente, String cognome_docente) {
		
		
		
		
	}
	
	public void agg_docente(Docente docente) {
		
		
	}
	
	public Docente ricerca_docente(String nome, String cognome) {
		return null;
		
	}
	
	public Docente ricerca_docente(String matricola) {
		return null;
		
	}
	
	public void canc_studente(String matricola) {
		
		
	}
	
	public void agg_corso(int codice, String denominazione_corso, int CFU) {
		
	}
	
	public void agg_corso(Corso corso) {
		
	}
	
	public Corso ricerca_corso(int codice) {
		return null;
		
	}
	
	public ArrayList<Corso> ricerca_corso_denominazione(String denominazione_corso){
		return null;
		
	}
	
	public ArrayList<Corso> ricerca_corso_docente(String matricola_docente){
		return null;
		
	}
	
	public ArrayList<Corso> ricerca_corso_docente(String nome_docente, String cognome_docente){
		return null;
		
	}
	
	public void canc_corso(int codice) {
		
		
	}
	
	public void assoc_doc_corso(String matricola_docente, int codice_corso, int annoAccademico) {
		
		
	}
	
	public void crea_appello(Appello a) {
		
		
	}
	
	public void getCorsiAssociati(String matricola_docente) {
		
		
		
	}
	
	public Appello cerca_appello(int codiceCorso) {
		return null;
				
	}
	
	public Studente cerca_prenotato(Appello a) {
		return null;
		
		
	}
	
	public void prenota_appello(Appello a, Studente s) {
		
		
	}
	
	public void crea_verbale(Appello a) {
		
	}
	
	public void agg_studente(Appello a, Studente s) {
		
	}
	
	public void canc_studente(Appello a, Studente s) {
		
		
	}
	
	public void regista_esame(Appello a, Studente s, int voto, boolean lode) {
		
		
	}
	
	public void chiudi_verbale() {
		
	}
	
	public void stampa_report(int codice_corso, String matricola_docente, int annoAccademico) {
		
	}
	
	public ArrayList<Studente> prelievo_dati_studenti() {
		return null;
		
		
	}
	
	
	

}