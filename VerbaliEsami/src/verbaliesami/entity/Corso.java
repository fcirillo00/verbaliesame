package verbaliesami.entity;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.persistance.AppelloDAO;
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
		
		this.appelli = new ArrayList<Appello>();
		this.titolarita_docenti = new ArrayList<Titolarita>();
		
		try {
			this.appelli = AppelloDAO.read(this.getCodice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun appello trovato");
		}
		
		try {
			this.titolarita_docenti = TitolaritaDAO.readFromCorso(this.getCodice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun docente titolare trovato");
		}
	}

	
	public void mostraCorso() {
		System.out.println("Corso di " + this.denominazione + ":");
		System.out.format("CFU: %d\n", this.cfu);
		System.out.format("Codice: %d\n", this.codice); 
		try {
			for (int i=0; i < this.getTitolarita_docenti().size(); i++) {
				Docente d = this.getTitolarita_docenti().get(i).getDocente();
				System.out.format("Docente: %s %s", d.getNome(), d.getCognome());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun docente associato trovato");
			return;
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
			System.out.println("Nessun docente associato.");
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

	public ArrayList<Appello> getAppelli(){
		return appelli;
	}


	public ArrayList<Titolarita> getTitolarita_docenti() throws SQLException {
		return this.titolarita_docenti;
	}
	
	public void aggiungiDocenteTitolarita(Docente doc, int annoAccademico) {
		
		Titolarita titolarita = new Titolarita(doc.getMatricola(), this.getCodice(), annoAccademico);
		
		this.titolarita_docenti.add(titolarita);
		try {
			TitolaritaDAO.create(this.getCodice(), doc.getMatricola(), annoAccademico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Impossibile aggiungere titolarita'");
		}
		
	}
	

	public boolean equals (Corso c) {
		if (this.cfu == c.cfu && this.codice == c.codice && this.denominazione.equals(c.denominazione) ) {
			return true;
		} else {
			return false;
		}
	}

}
