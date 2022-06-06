package verbaliesami.entity;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.ValutazioneDAO;
import verbaliesami.persistance.VerbaleDAO;

public class Studente {

	private String nome;
	private String cognome;
	private String matricola;
	private String username;
	private String password;
	private int pin;
	
	private ArrayList<Appello> appelliPrenotati;
	private ArrayList<Valutazione> valutazioniConseguite;
	
	
	public Studente(String nome, String cognome, String matricola,String username, String password, int pin) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.username = username;
		this.password = password;
		this.pin = pin;
		
		appelliPrenotati = new ArrayList<Appello>();
		valutazioniConseguite = new ArrayList<Valutazione>();
		
	}
	
	public Studente(Studente s) {
		
		this.nome = s.nome;
		this.cognome = s.cognome;
		this.matricola = s.matricola;
		this.username = s.username;
		this.password = s.password;
		this.pin = s.pin;	
		
		try {
			appelliPrenotati = PrenotazioneDAO.readAppelli(this.getMatricola());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun appello prenotato trovato");
		}
		try {
			valutazioniConseguite = ValutazioneDAO.readValutazioniStudente(this.getMatricola());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessuna valutazione conseguita trovata");
		}
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public void mostraInfoStudente() {
		
		System.out.println("Studente con matricola: " + matricola);
		System.out.println("\tCOGNOME: " + cognome + "  NOME: " + nome);		
		
	}

	public ArrayList<Appello> getAppelliPrenotati(){
		
		return appelliPrenotati;
	}

	public void aggiungiAppelloPrenotato(Appello appelloPrenotato) throws NullPointerException, SQLException {
		this.appelliPrenotati.add(appelloPrenotato);
		PrenotazioneDAO.create(this.getMatricola(), AppelloDAO.readId(appelloPrenotato));
	}

	public ArrayList<Valutazione> getValutazioniConseguite() {
		
		return valutazioniConseguite;
	}

	public void aggiungiValutazioneConseguita(Valutazione valutazioneConseguita) throws NullPointerException, SQLException {
		this.valutazioniConseguite.add(valutazioneConseguita);
		ValutazioneDAO.create(valutazioneConseguita.getVoto(), valutazioneConseguita.getArgomenti_trattati(), VerbaleDAO.readIdFromAppello(AppelloDAO.readId(valutazioneConseguita.getVerbale_rif().getAppello_riferito())), valutazioneConseguita.getEsaminato().getMatricola());
	}
	
	
}
