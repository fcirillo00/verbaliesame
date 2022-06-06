package verbaliesami.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.VerbaleDAO;

public class Appello {
	
	public enum Sede{Aula, Laboratorio, Altro}	
	private Calendar data;
	private Calendar scadenza;
	private String note;
	private Sede sede;
	private Corso corso;
	private Docente docente;
	private Verbale verbalizzazione;
	private List<Studente> prenotazione;
	
	//TODO Nel control, quando si inserisce il mese, fare -1
	
	public Appello(Calendar data, Calendar scadenza, String note, String sede, int codice_corso, String matricola_docente) {
		
		this.data = new GregorianCalendar();
		this.data = data;
		this.scadenza = new GregorianCalendar();
		this.scadenza = scadenza;
		this.note = note;
		if(sede.compareTo("AULA") == 0) {
			this.sede = Sede.Aula;
		}else if(sede.compareTo("LABORATORIO") == 0){
			this.sede = Sede.Laboratorio;
		}else {
			this.sede = Sede.Altro;
		}
		
		try {
			this.corso = CorsoDAO.read(codice_corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Corso non trovato");
			return;
		}
		
		try {
			this.docente = DocenteDAO.readSafe(matricola_docente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Docente non trovato");
			return;
		}
		
		//Il verbale non esiste all'inizio, ma deve essere creato appositamente
		verbalizzazione = null;
		this.prenotazione = new ArrayList<Studente>();
		
	}
	
	public Appello(Appello a) {
		
		this.data = new GregorianCalendar();
		this.data = a.data;
		this.scadenza = new GregorianCalendar();
		this.scadenza = a.scadenza;
		this.note = a.note;
		this.sede = a.sede;
		this.corso = new Corso(a.corso);
		this.docente = new Docente(a.docente);
		try {
			this.verbalizzazione = new Verbale(a.getVerbale(VerbaleDAO.readIdFromAppello(AppelloDAO.readId(a))));
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Appello non valido");
		}
		
		this.prenotazione = new ArrayList<Studente>();
		
		try {
			this.prenotazione = PrenotazioneDAO.readStudenti(AppelloDAO.readId(this));
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun appello o nessuna prenotazione trovata");
		}
				
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void setData(Date data) {
		this.data.setTime(data);
	}
	
	public void setData(int giorno, int mese, int anno) {
		
		this.data.set(giorno, mese, anno);
		
	}
	
	public Calendar getData() {
		return data;
	}

	public void setScadenza(Calendar scadenza) {
		this.scadenza = scadenza;
	}
	
	public void setScadenza(Date data) {
		this.scadenza.setTime(data);
	}
	
	public void setScadenza(int giorno, int mese, int anno) {
		
		this.scadenza.set(giorno, mese, anno);
		
	}
	
	public Calendar getScadenza() {
		return scadenza;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public void mostraInfoAppello() {
		
		System.out.println("INFO APPELLO");
		System.out.println("\tCORSO: " + this.corso.getDenominazione());
		System.out.println("\tDOCENTE: " + this.docente.getCognome() + " " + this.docente.getNome());
		System.out.println("\tData appello: " + data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH)+1) + "/" +  data.get(Calendar.YEAR));
		System.out.println("\tData scadenza prenotazioni: " + scadenza.get(Calendar.DAY_OF_MONTH) + "/" + (scadenza.get(Calendar.MONTH)+1) + "/" +  scadenza.get(Calendar.YEAR));
		if(this.sede.compareTo(Sede.Aula) == 0) {
			System.out.println("\tSede: Aula");
		}else if(this.sede.compareTo(Sede.Laboratorio) == 0) {
			System.out.println("\tSede: Laboratorio");
		}else {
			System.out.println("\tSede: Altro");
		}
		System.out.println("NOTE:");
		System.out.println(this.getNote());

	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(String sede) {
		if(sede.compareTo("AULA") == 0) {
			this.sede = Sede.Aula;
		}else if(sede.compareTo("LABORATORIO") == 0){
			this.sede = Sede.Laboratorio;
		}else {
			this.sede = Sede.Altro;
		}
		
		
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
	
	public void setCorso(Corso corso) {
		this.corso = new Corso(corso);
	}
	
	public Corso getCorso(){
		return this.corso;
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
	
	public void setDocente(Docente docente) {
		this.docente = new Docente(docente);
	}
	
	public Docente getDocente() {
		return docente;
	}
	
	public void aggiungiStudentePrenotato(Studente s) throws NullPointerException, SQLException {
		
		this.prenotazione.add(s);
		PrenotazioneDAO.create(s.getMatricola(), AppelloDAO.readId(this));
		
	}
	
	
	public void mostraListaPrenotati() {
		
		System.out.println("LISTA PRENOTATI");
		
		Iterator<Studente> it = this.prenotazione.iterator();
		
		while(it.hasNext()) {
			it.next().mostraInfoStudente();
		}
		
		
	}
	
	public void creaVerbale(int id) throws SQLException {
		verbalizzazione = new Verbale(this);
		VerbaleDAO.create(id, this);
	}
	
	public Verbale getVerbale(int id) throws SQLException {
		verbalizzazione = VerbaleDAO.read(id);
		return verbalizzazione;
	}
	

}


