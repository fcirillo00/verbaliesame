/* 	DOCUMENTAZIONE
 * 
 * public static VerbaliManagementSystem getInstance()
 * DESCRIZIONE: Permette di ottenere l'unica istanza del VerbaliManagementSystem per effettuare operazioni di controllo
 * PRECONDIZIONI: nessuna
 * POSTCONDIZIONI: Viene restituita l'istanza del VerbaliManagementSystem.
 * 
 * public boolean agg_docente(String matricola, String nome_docente, String cognome_docente, String username, String password)
 * DESCRIZIONE: Permette di aggiungere un nuovo docente alla base di dati, dando in ingresso il valore dei suoi campi.
 * PRECONDIZIONI: Il docente non deve essere già presente ed i campi devono essere correttamente compilati: nome e cognome composti da solo lettere,
 * 				  matricola composta da soli caratteri alfanumerici e di 9 caratteri, username non vuoto
 * POSTCONDIZIONI: Il nuovo docente e' inserito nella base di dati
 * 
 * public boolean agg_docente(Docente docente)
 * DESCRIZIONE: Permette di aggiungere un nuovo docente alla base di dati, dando in ingresso un oggetto docente.
 * PRECONDIZIONI: Il docente non deve essere già presente ed le risorse dell'oggetto devono essere correttamente istanziate: nome e cognome composti 
 *                da solo lettere, matricola composta da soli caratteri alfanumerici e di 9 caratteri, username non vuoto
 * POSTCONDIZIONI: Il nuovo docente e' inserito nella base di dati
 * 
 * public ArrayList<Docente> ricerca_docente(String nome, String cognome)
 * DESCRIZIONE: Restituisce la lista dei docenti presenti nella base di dati con campi nome e cognome uguali ai parametri in ingresso.
 * PRECONDIZIONI: I parametri devono essere correttamente compilati: composti da sole lettere.
 * POSTCONDIZIONI: Viene restituita la lista dei docenti corrispondenti. Se nessun docente corrispondente e' presente nella base di dati, il valore di
 * 				   ritorno e' null.
 * 
 * public Docente ricerca_docente(String matricola)
 * DESCRIZIONE: Restituisce il docente presente nella base di dati con campo matricola uguale al parametro in ingresso.
 * PRECONDIZIONI: Il parametro matricola deve essere correttamente compilato: composto da caratteri alfanumerici e di lunghezza pari a 9 caratteri.
 * POSTCONDIZIONI: Viene restituito il singolo docente con matricola pari a quella inserita. Se nessun docente corrispondente e' presente nella base di
 * 				   dati, il valore di ritorno e' null
 * 
 * public boolean canc_docente(String matricola)
 * DESCRIZIONE: Elimina il docente dalla base di dati con matricola pari a quella inserita.
 * PRECONDIZIONI: Il docente deve essere presente nella base di dati
 * POSTCONDIZIONI: Il docente viene eliminato dalla base di dati
 * 
 * public boolean agg_corso(int codice, String denominazione_corso, int CFU)
 * DESCRIZIONE: Permette di aggiungere un nuovo corso alla base di dati, dando in ingresso il valore dei suoi campi.
 * PRECONDIZIONI: Il corso non deve essere già presente nella base di dati.
 * POSTCONDIZIONI: Il nuovo corso e' inserito nella base di dati
 * 
 * public boolean agg_corso(Corso corso)
 * DESCRIZIONE: Permette di aggiungere un nuovo corso alla base di dati, dando in ingresso un oggetto corso.
 * PRECONDIZIONI: Il corso non deve essere già presente nella base di dati.
 * POSTCONDIZIONI: Il nuovo corso e' inserito nella base di dati 
 * 
 * public Corso ricerca_corso(int codice)
 * DESCRIZIONE: Restituisce il corso presente nella base di dati con campo codice uguale al parametro in ingresso.
 * PRECONDIZIONI: nessuna
 * POSTCONDIZIONI: Viene restituito il singolo corso con codice pari a quello inserito. Se nessun corso corrispondente e' presente nella base di
 * 				   dati, il valore di ritorno e' null
 * 
 * public ArrayList<Corso> ricerca_corso_denominazione(String denominazione_corso)
 * DESCRIZIONE: Restituisce i corsi presente nella base di dati con campo denominazione uguale al parametro in ingresso.
 * PRECONDIZIONI: Il parametro può contenere solo caratteri alfabetici e spazi.
 * POSTCONDIZIONI: Viene restituita la lista di corsi con denominazione pari a quella inserita. Se nessun corso corrispondente e' presente nella base di
 * 				   dati, il valore di ritorno e' null
 * 
 * public ArrayList<Corso> ricerca_corso_docente(String matricola_docente)
 * DESCRIZIONE: Restituisce i corsi presente nella base di dati associati al docente con matricola pari a quella inserita.
 * PRECONDIZIONI: il parametro può contenere solo 9 caratteri alfanumerici 
 * POSTCONDIZIONI: Viene restituita la lista di corsi associati al docente con matricola pari a quella inserita. Se nessun corso corrispondente e' 
 * 				   presente nella base di dati, il valore di ritorno e' null
 * 
 * public ArrayList<Corso> ricerca_corso_docente(String nome_docente, String cognome_docente)
 * DESCRIZIONE: Restituisce i corsi presente nella base di dati associati ai docenti con nome e cognome pari a quelli inseriti.
 * PRECONDIZIONI: i due parametri possono contenere solo caratteri alfabetici e spazi.
 * POSTCONDIZIONI: Viene restituita la lista di corsi associati ai docenti con nome e cognome pari a quelli inseriti. Se nessun corso corrispondente e' 
 * 				   presente nella base di dati, il valore di ritorno e' null
 * 
 * public boolean canc_corso(int codice)
 * DESCRIZIONE: Elimina il docecorsonte dalla base di dati con codice pari a quello inserito.
 * PRECONDIZIONI: Il corso deve essere presente nella base di dati
 * POSTCONDIZIONI: Il corso viene eliminato dalla base di dati
 * 
 * public boolean assoc_doc_corso(String matricola_docente, int codice_corso, int annoAccademico)
 * DESCRIZIONE: Viene associato il docente identificato dalla matricola ed il corso identificato dal codice, con la specifica dell'anno accademico
 * PRECONDIZIONI: Il docente ed il corso devono essere presenti nella base di dati, e tale associzione non deve essere già stata fatta.
 * POSTCONDIZIONI: Viene registrata l'associazione nella base di dati
 * 
 * public boolean crea_appello(Appello a, int id_appello) 
 * DESCRIZIONE: Viene registrato un nuovo appello
 * PRECONDIZIONI: L'appello non deve essere già stato inserito e id_appello e' univoco e legato a tale appello.
 * POSTCONDIZIONI: Viene registrato il nuovo appello nella base di dati.
 * 
 * public ArrayList<Corso> getCorsiAssociati(String matricola_docente)
 * DESCRIZIONE: Accede alla lista dei corsi a cui il docente, indicato dalla matricola, è associato.
 * PRECONDIZIONI: Il docente è gia' registrato ed e' associato ad almeno un corso.
 * POSTCONDIZIONI: Viene restituira la lista dei corsi a cui e' associato il docente
 * 
 * public ArrayList<Appello> cerca_appello(int codiceCorso)
 * DESCRIZIONE: Accede alla lista degli appelli legati ad un corso, identificato dal proprio codice univoco
 * PRECONDIZIONI: Il corso deve essere presente nel DB e ci devono essere appelli legati al corso identificato dal codice
 * POSTCONDIZIONI: Viene restituita la lista degli appelli legati al corso.
 * 
 * public boolean prenota_appello(Appello a, Studente s)
 * DESCRIZIONE: Permette ad uno studente di prenotare un appello
 * PRECONDIZIONI: Lo studente e l'appello devono essere presenti nel DB
 * POSTCONDIZIONI: Lo studente viene prenotato all'appello
 * 
 * public Studente login (String username, String password) 
 * DESCRIZIONE: Permette ad uno studente di effettuare il login
 * PRECONDIZIONI: Lo studente deve essere registrato nel database, con campi username e password uguali ai parametri in ingresso
 * POSTCONDIZIONI: Viene restituito l'oggetto Studente contenente le informazioni dello Studente specificato da username e password
 * 
 * LE ALTRE FUNZIONI, LE QUALI NON SONO STATE DOCUMENTATE, NON HANNO IMPLEMENTAZIONE
 * 
 * 
 */








package verbaliesami.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.entity.*;
import verbaliesami.entity.Appello.Sede;
import verbaliesami.exceptions.CampoTroppoLungoException;
import verbaliesami.exceptions.CognomeInvalidoException;
import verbaliesami.exceptions.DataInvalidaException;
import verbaliesami.exceptions.DocenteNonPresenteException;
import verbaliesami.exceptions.MatricolaInvalidaException;
import verbaliesami.exceptions.NomeInvalidoException;
import verbaliesami.exceptions.UsernameVuotoException;
import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.StudenteDAO;
import verbaliesami.persistance.TitolaritaDAO;



public class VerbaliManagementSystem {

	private static VerbaliManagementSystem instance = null;

	private VerbaliManagementSystem(){
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
			
			if((!nome_docente.matches(pattern)) || (nome_docente.isBlank())){
				throw new NomeInvalidoException("Nome non valido");
			} else if(nome_docente.length()>50){
				throw new CampoTroppoLungoException("Nome troppo lungo");
			}
			if(!cognome_docente.matches(pattern) || (cognome_docente.isBlank())) {
				throw new CognomeInvalidoException("Cognome non valido");
			} else if(cognome_docente.length()>50){
				throw new CampoTroppoLungoException("Cognome troppo lungo");
			}
			if(!matricola.matches(matricola_pattern) || (matricola.length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			}
			if(username.isBlank()) {
				throw new UsernameVuotoException("Username vuoto.");
			} else if(username.length()>50) {
				throw new CampoTroppoLungoException("Username troppo lungo");
			}
			if(password.length()>50) {
				throw new CampoTroppoLungoException("Password troppo lunga");
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
		} catch (UsernameVuotoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (CampoTroppoLungoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean agg_docente(Docente docente) {
		
		try {
			
			String pattern = "[a-zA-Z]*";
			String pattern2 = "[' ']";
			String matricola_pattern = "[a-zA-Z0-9]*";
			
			if((!docente.getNome().matches(pattern) && (!docente.getNome().matches(pattern2))) || (docente.getNome().isBlank())){
				throw new NomeInvalidoException("Nome non valido");
			}else if(docente.getNome().length()>50){
				throw new CampoTroppoLungoException("Nome troppo lungo");
			}
			if((!docente.getCognome().matches(pattern)) && (!docente.getCognome().matches(pattern2))|| (docente.getCognome().isBlank())) {
				throw new CognomeInvalidoException("Cognome non valido");
			} else if(docente.getCognome().length()>50){
				throw new CampoTroppoLungoException("Cognome troppo lungo");
			}
			if(!docente.getMatricola().matches(matricola_pattern) || (docente.getMatricola().length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			}if(docente.getUsername().isBlank()) {
				throw new UsernameVuotoException("Username vuoto.");
			} else if(docente.getUsername().length()>50){
				throw new CampoTroppoLungoException("Username troppo lungo");
			}
			if(docente.getPassword().length()>50){
				throw new CampoTroppoLungoException("Password troppo lunga");
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
		} catch (UsernameVuotoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (CampoTroppoLungoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<Docente> ricerca_docente(String nome, String cognome) {
		
		ArrayList<Docente> lista_docenti = null;
		
		String pattern = "[a-zA-Z]*";	
		String pattern2 = "[' ']";
		
		try {
			
			if((!nome.matches(pattern) && (!nome.matches(pattern2))) || (nome.isBlank())){
				throw new NomeInvalidoException("Nome non valido");
			}else if(nome.length()>50){
				throw new CampoTroppoLungoException("Nome troppo lungo");
			}
			if(!cognome.matches(pattern) && (!nome.matches(pattern2)) || (cognome.isBlank())) {
				throw new CognomeInvalidoException("Cognome non valido");
			}else if(cognome.length()>50){
				throw new CampoTroppoLungoException("Cognome troppo lungo");
			}
			if(DocenteDAO.readSafe(nome, cognome) == null) {
				throw new DocenteNonPresenteException("Nessun Docente trovato con i parametri forniti");
			}
			
			lista_docenti = new ArrayList<Docente>(DocenteDAO.readSafe(nome, cognome));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
		} catch (NomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} catch (CognomeInvalidoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} catch (DocenteNonPresenteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} catch (CampoTroppoLungoException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
		return lista_docenti;
	}
	
	public Docente ricerca_docente(String matricola) {
		
		Docente doc = null;
		

		String matricola_pattern = "[a-zA-Z0-9]*";	
		
		try {
			
			if((!matricola.matches(matricola_pattern)) || (matricola.length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			} else if(DocenteDAO.readSafe(matricola) == null) {
				throw new DocenteNonPresenteException("Docente non presente nel DB");
			}
			
			doc = new Docente(DocenteDAO.readSafe(matricola));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
		} catch (MatricolaInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} catch (DocenteNonPresenteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
		return doc;
		
	}
	
	public boolean canc_docente(String matricola) {
		
		String matricola_pattern = "[a-zA-Z0-9]*";
		
		try {
			
			if((!matricola.matches(matricola_pattern)) || (matricola.length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			} else if(DocenteDAO.readSafe(matricola) == null) {
				throw new DocenteNonPresenteException("Docente non presente nel DB");
			}
			
			DocenteDAO.delete(matricola);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Nessun docente trovato");
			return false;
		} catch (MatricolaInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (DocenteNonPresenteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
		ArrayList<Corso> c = new ArrayList<Corso>();
		String pattern = "[a-zA-Z ]*";		

		if (!denominazione_corso.matches(pattern)) { System.out.println(denominazione_corso+" non e' un nome valido"); return c; }
		
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
		
		ArrayList<Corso> c = new ArrayList<Corso>();
		String matricola_pattern = "^[a-zA-Z0-9]{9}$";
		
		if (!matricola_docente.matches(matricola_pattern)) { System.out.println(matricola_docente+" non e' una matricola valida"); return c; }
		
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
		ArrayList<Corso> c = new ArrayList<Corso>();
		String pattern = "[a-zA-Z ]*";

		if (!nome_docente.matches(pattern) || !cognome_docente.matches(pattern)) {System.out.println(nome_docente+" "+ cognome_docente + " non e' una denominazione valida"); return c; }
		
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
		Calendar ref = new GregorianCalendar();
		ref.set(1225, 0, 1);
		Calendar ref2 = new GregorianCalendar();
		ref2.set(9999, 11, 31);
		
		String matricola_pattern = "[a-zA-Z0-9]*";
		String sede = "";
		
		
		
		try {
			
			if (a.getData().after(ref2) || a.getScadenza().after(ref2)) {
				throw new DataInvalidaException("Data non valida, posteriore al 31/12/9999");
			}
			
			if (a.getScadenza().after(a.getData())) {
				Calendar temp = new GregorianCalendar();
				temp.setTimeInMillis(a.getData().getTimeInMillis() - 86400000);
				a.setScadenza(temp);
			}
			
			
			if (a.getData().before(ref) || a.getScadenza().before(ref)) {
				throw new DataInvalidaException("Data non valida, precendente al 01/01/1225");
			}
			
			if(!a.getDocente().getMatricola().matches(matricola_pattern) || (a.getDocente().getMatricola().length() != 9)) {
				throw new MatricolaInvalidaException("Matricola non valida per inserimento caratteri non alfanumerici o dimensione diversa da 9.");
			}
			
			if (id_appello <= 0) {
				throw new IOException("Id non valido");
			}
			
			if(a.getSede() == Sede.Aula){
				sede = "AULA";
			}else if(a.getSede() == Sede.Laboratorio) {
				sede = "LABORATORIO";
			}else {
				sede = "ALTRO";
			}
						
			AppelloDAO.create(id_appello, a.getData(), a.getScadenza(), a.getNote(), sede, a.getCorso().getCodice(), a.getDocente().getMatricola());
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errore: Connessione non riuscita o Appello non inseribile.");
			System.out.println("Controllare se l'appello e' gia' stato inserito.");
			System.out.println("Se non fosse gia' stato inserito, controllare i dati.");
			return false;
		} catch (MatricolaInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (DataInvalidaException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			System.out.println("Errore, corso non esistente o docente non esistente");
			return false;
		} catch (IOException e) {
			System.out.println("Errore, id invalido");
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
		try {
			PrenotazioneDAO.create(s.getMatricola(), AppelloDAO.readId(a));
			return true;
		} catch (SQLException e) {
			System.out.println("Errore, appello non trovato nel DB per la prenotazione o appello gia' prenotato.");
			return false;
		} catch (NullPointerException e) {
			System.out.println("Appello non inseribile, corso o docente non trovato.");
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