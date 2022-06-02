package verbaliesami.entity;

public class Studente implements Cloneable{

	private String nome;
	private String cognome;
	private String matricola;
	private String username;
	private String password;
	private int pin;
	
	public Studente(String nome, String cognome, String matricola,String username, String password, int pin) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.username = username;
		this.password = password;
		this.pin = pin;		
	}
	
	public Studente(Studente s) {
		
		this.nome = s.nome;
		this.cognome = s.cognome;
		this.matricola = s.matricola;
		this.username = s.username;
		this.password = s.password;
		this.pin = s.pin;		
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
	
	
	
}
