package verbaliesami.entity;

import java.util.ArrayList;

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
		
		appelliPrenotati = new ArrayList<Appello>(s.getAppelliPrenotati());
		valutazioniConseguite = new ArrayList<Valutazione>(s.getValutazioniConseguite());
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

	public ArrayList<Appello> getAppelliPrenotati() {
		return appelliPrenotati;
	}

	public void aggiungiAppelloPrenotato(Appello appelloPrenotato) {
		this.appelliPrenotati.add(appelloPrenotato);
	}

	public ArrayList<Valutazione> getValutazioniConseguite() {
		return valutazioniConseguite;
	}

	public void aggiungiValutazioneConseguite(Valutazione valutazioneConseguita) {
		this.valutazioniConseguite.add(valutazioneConseguita);
	}
	
	
}
