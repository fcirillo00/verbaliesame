package verbaliesami.control;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.*;
import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.TitolaritaDAO;

public class VerbaliManagementSystem {

	private static VerbaliManagementSystem instance = null;

	protected VerbaliManagementSystem(){
		super();
	}

	public static VerbaliManagementSystem getInstance(){
		if (instance == null) {
			instance = new VerbaliManagementSystem(); 
		}
		return instance;
	}
	
	public void agg_docente(String matricola, String nome_docente, String cognome_docente, String username, String password) {
		
		try {
			DocenteDAO.create(nome_docente, cognome_docente, matricola, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Docente gi� esistente.");
		}
		
	}
	
	public void agg_docente(Docente docente) {
		
		try {
			DocenteDAO.create(docente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Docente gi� esistente.");
		}
		
	}
	
	public ArrayList<Docente> ricerca_docente(String nome, String cognome) {
		
		ArrayList<Docente> lista_docenti = null;
		
		try {
			lista_docenti = new ArrayList<Docente>(DocenteDAO.readSafe(nome, cognome));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
		}
		
		return lista_docenti;
	}
	
	public Docente ricerca_docente(String matricola) {
		
		Docente doc = null;
		
		try {
			doc = new Docente(DocenteDAO.readSafe(matricola));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
		}
		
		return doc;
		
	}
	
	public void canc_docente(String matricola) {
		
		try {
			DocenteDAO.delete(matricola);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
		}
		
	}
	
	public void agg_corso(int codice, String denominazione_corso, int CFU) {
		
		try {
			CorsoDAO.create(codice, denominazione_corso, CFU);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Corso gi� esistente.");
		}
		
	}
	
	public void agg_corso(Corso corso) {
		try {
			CorsoDAO.create(corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Corso gi� esistente.");
		}
	}
	
	public Corso ricerca_corso(int codice) {
		
		Corso c = null;
		
		try {
			c = new Corso(CorsoDAO.read(codice));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Corso non trovato.");
		}
		
		return c;
		
	}
	
	public ArrayList<Corso> ricerca_corso_denominazione(String denominazione_corso){
		ArrayList<Corso> c = null;
		
		try {
			c = new ArrayList<Corso>(CorsoDAO.read(denominazione_corso));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun Corso trovato.");
		}
		
		return c;
	}
	
	public ArrayList<Corso> ricerca_corso_docente(String matricola_docente){
		ArrayList<Corso> c = null;
		
		try {
			c = new ArrayList<Corso>(CorsoDAO.readFromMatricola(matricola_docente));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun Corso trovato.");
		}
		
		return c;
		
	}
	
	public ArrayList<Corso> ricerca_corso_docente(String nome_docente, String cognome_docente){
		ArrayList<Corso> c = null;
		
		try {
			c = new ArrayList<Corso>(CorsoDAO.readFromNomeCognome(nome_docente, cognome_docente));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun Corso trovato.");
		}
		
		return c;
		
	}
	
	public void canc_corso(int codice) {
		
		try {
			CorsoDAO.delete(codice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun Corso trovato.");
		}
		
	}
	
	public void assoc_doc_corso(String matricola_docente, int codice_corso, int annoAccademico) {
		
		try {
			TitolaritaDAO.create(codice_corso, matricola_docente, annoAccademico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Associzione non possibile.");
			System.out.println("Controllare se il corso � stato gi� associato a tale docente");
		}
	}
	
	public void crea_appello(Appello a, int id_appello) {
		
		String sede = "";
		
		if(a.getSede() == Appello.Sede.Aula) {
			sede= "AULA";
		}else if(a.getSede() == Appello.Sede.Laboratorio){
			sede= "LABORATORIO";
		}else {
			sede = "ALTRO";
		}
		
		try {
			AppelloDAO.create(id_appello, a.getData(), a.getScadenza(), a.getNote(), sede, a.getCorso().getCodice(), a.getDocente().getMatricola());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Appello non inseribile.");
			System.out.println("Controllare se l'appello � gi� stato inserito.");
			System.out.println("Se non fosse gi� stato inserito, controllare i dati.");
		}
		
	}
	
	public ArrayList<Corso> getCorsiAssociati(String matricola_docente) {
		
		Docente d = ricerca_docente(matricola_docente);
		
		ArrayList<Corso> lista_corsi = d.getCorsiAssociati();
		
		return lista_corsi;
	}
	
	public ArrayList<Appello> cerca_appello(int codiceCorso) {
		ArrayList<Appello> a = null;
		try {
			a = new ArrayList<Appello>(AppelloDAO.read(codiceCorso));
		} catch(SQLException e) {
			System.out.println("Errore: connessione non riuscita o appello non trovato");
		}
		return a; 
				
	}
	
	public Studente cerca_prenotato(Appello a) {

		return null;
		
	}
	
	public void prenota_appello(Appello a, Studente s) {
		a.aggiungiStudentePrenotato(s);
		try {
			PrenotazioneDAO.create(s.getMatricola(), AppelloDAO.readId(a));
		} catch (SQLException e) {
			System.out.println("Errore, appello non trovato nel DB per la prenotazione.");
		}
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