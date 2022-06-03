package verbaliesami.entity;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.persistance.TitolaritaDAO;

public class Corso {

	
	private int codice;
	private String denominazione;
	private int cfu;
	private ArrayList<Appello> appelli;
	private ArrayList<Titolarita> titolarita_docenti;
	
	
	public Corso (int codice, String denominazione, int cfu) {
		this.codice = codice;
		this.denominazione = denominazione;
		this.cfu = cfu;
		
		this.appelli = new ArrayList<Appello>();
		this.titolarita_docenti = new ArrayList<Titolarita>();
		
	}
	
	public Corso(Corso c) {
		this.codice = c.getCodice();
		this.denominazione = c.getDenominazione();
		this.cfu = c.getCfu();
		
		appelli = new ArrayList<Appello>(c.getAppelli());
		titolarita_docenti = new ArrayList<Titolarita>(c.getTitolarita_docenti());
	}

	
	public void mostraCorso() {
		System.out.println("Corso di " + this.denominazione + ":");
		System.out.format("CFU: %d\n", this.cfu);
		System.out.format("Codice: %d\n", this.codice); 
		for (int i=0; i < this.titolarita_docenti.size(); i++) {
			Docente d = this.titolarita_docenti.get(i).getDocente();
			System.out.format("Docente: %s %s", d.getNome(), d.getCognome());
		}

		System.out.println("");
	}
	
	public ArrayList<Docente> getDocentiAssociati() {
		
		ArrayList<Docente> docenti = new ArrayList<Docente>();
		try {
			ArrayList<Titolarita> lista = TitolaritaDAO.readFromCorso(this.codice);
			for (int i=0; i<lista.size(); i++) {
				docenti.add(lista.get(i).getDocente());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun corso associato.");
		}
		return docenti;
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

	public ArrayList<Appello> getAppelli() {
		return appelli;
	}

	public void aggiungiAppello(Appello appello) {
		this.appelli.add(appello);
	}

	public ArrayList<Titolarita> getTitolarita_docenti() {
		return titolarita_docenti;
	}

	public void aggiungiDocenteTitolare(Titolarita titolarita) {
		this.titolarita_docenti.add(titolarita);
	}
	
	
}
