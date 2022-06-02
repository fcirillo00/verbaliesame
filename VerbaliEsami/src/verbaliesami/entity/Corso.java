package verbaliesami.entity;

public class Corso {
	// codice
	// denominazione
	// cfu
	
	private int codice;
	private String denominazione;
	private int cfu;
	
	public Corso (int codice, String denominazione, int cfu) {
		this.codice = codice;
		this.denominazione = denominazione;
		this.cfu = cfu;
	}

	
	public void mostraCorso() {
		System.out.println("Corso di " + this.denominazione + ":");
		System.out.format("CFU: %d\n", cfu);
		System.out.format("Codice: %d\n", codice);
		// TODO
		// stampa docente associato
		System.out.println("");
	}
	
	// getters and setters
	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	
}
