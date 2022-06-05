package verbaliesami.control;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.entity.*;
import verbaliesami.exceptions.CognomeInvalidoException;
import verbaliesami.exceptions.MatricolaInvalidaException;
import verbaliesami.exceptions.NomeInvalidoException;
import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.StudenteDAO;
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
	
	public boolean agg_docente(String matricola, String nome_docente, String cognome_docente, String username, String password) {
		
		try {
			
			String pattern = "[a-zA-Z]*";
			String matricola_pattern = "[a-zA-Z0-9]*";
			
			if((!nome_docente.matches(pattern)) || (nome_docente.compareTo("") == 0)){
				throw new NomeInvalidoException("Nome non valido");
			}
			if(!cognome_docente.matches(pattern) || (cognome_docente.compareTo("") == 0)) {
				throw new CognomeInvalidoException("Cognome non valido");
			}
			if(!matricola.matches(matricola_pattern) || (matricola.length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			}
			
			
			DocenteDAO.create(nome_docente, cognome_docente, matricola, username, password);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Docente impossibile da inserire.");
			System.out.println("Ricontrollare dati in ingresso");
			System.out.println("");
			return false;
		} catch (NomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (CognomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (MatricolaInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean agg_docente(Docente docente) {
		
		try {
			
			String pattern = "[a-zA-Z]*";
			String matricola_pattern = "[a-zA-Z0-9]*";
			
			if((!docente.getNome().matches(pattern)) || (docente.getNome().compareTo("") == 0)){
				throw new NomeInvalidoException("Nome non valido");
			}
			if(!docente.getCognome().matches(pattern) || (docente.getCognome().compareTo("") == 0)) {
				throw new CognomeInvalidoException("Cognome non valido");
			}
			if(!docente.getMatricola().matches(matricola_pattern) || (docente.getMatricola().length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			}
			
			
			DocenteDAO.create(docente);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Docente gia' esistente.");
			System.out.println("Ricontrollare dati in ingresso");
			System.out.println("");
			return false;
		} catch (NomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (CognomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (MatricolaInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
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
	
	public boolean canc_docente(String matricola) {
		
		try {
			DocenteDAO.delete(matricola);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
			return false;
		}
		
	}
	
	public boolean agg_corso(int codice, String denominazione_corso, int CFU) {
		
		try {
			CorsoDAO.create(codice, denominazione_corso, CFU);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Corso gia' esistente.");
			return false;
		}
		
	}
	
	public boolean agg_corso(Corso corso) {
		try {
			CorsoDAO.create(corso);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Corso gia' esistente.");
			return false;
		}
	}
	
	public Corso ricerca_corso(int codice) {
		
		Corso c = null;
		
		try {
			c = CorsoDAO.read(codice);
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
	
	public boolean canc_corso(int codice) {
		
		try {
			CorsoDAO.delete(codice);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun Corso trovato.");
			return false;
		}
		
	}
	
	public boolean assoc_doc_corso(String matricola_docente, int codice_corso, int annoAccademico) {
		
		try {
			TitolaritaDAO.create(codice_corso, matricola_docente, annoAccademico);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Associzione non possibile.");
			System.out.println("Controllare se il corso e' stato gia' associato a tale docente");
			return false;
		}
	}
	
	public boolean crea_appello(Appello a, int id_appello) {
		
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
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Appello non inseribile.");
			System.out.println("Controllare se l'appello e' gia' stato inserito.");
			System.out.println("Se non fosse gia' stato inserito, controllare i dati.");
			return false;
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
	
	public boolean prenota_appello(Appello a, Studente s) {
		a.aggiungiStudentePrenotato(s);
		try {
			PrenotazioneDAO.create(s.getMatricola(), AppelloDAO.readId(a));
			return true;
		} catch (SQLException e) {
			System.out.println("Errore, appello non trovato nel DB per la prenotazione o appello già prenotato.");
			return false;
		}
	}
	
	public Studente login (String username, String password) {
		Studente s = null;
		try {
			s = StudenteDAO.login(username, password);
		} catch (SQLException e) {
			System.out.println("Errore nel DB.");
		}
		return s;
	}
	
	public boolean crea_verbale(Appello a) {
		return false;
	}
	
	public boolean agg_studente(Appello a, Studente s) {
		return false;
	}
	
	public boolean canc_studente(Appello a, Studente s) {
		
		return false;
	}
	
	public boolean regista_esame(Appello a, Studente s, int voto, boolean lode) {
		
		return false;
	}
	
	public boolean chiudi_verbale() {
		return false;
	}
	
	public boolean stampa_report(int codice_corso, String matricola_docente, int annoAccademico) {
		return false;
	}
	
	public boolean prelievo_dati_studenti() {
		return false;
	}
	

	

}