package verbaliesami.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import verbaliesami.entity.Appello.Sede;
import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.TitolaritaDAO;

public class Docente {

	private String nome;
	private String cognome;
	private String matricola;
	private String username;
	private String password;
	
	private ArrayList<Titolarita> corsi_associati;
	private ArrayList<Appello> appelli;
	
	public Docente (String nome, String cognome, String matricola, String username, String password) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.username = username;
		this.password = password;
		
		corsi_associati = new ArrayList<Titolarita>();
		appelli = new ArrayList<Appello>();
		
	}
	
	
	
	public Docente (Docente d) {
		
		this.nome = d.nome;
		this.cognome = d.cognome;
		this.matricola = d.matricola;
		this.username = d.username;
		this.password = d.password;
	
		corsi_associati = new ArrayList<Titolarita>();
		
		try {
			
			this.corsi_associati = TitolaritaDAO.readFromDocente(this.getMatricola());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessuna titolarita' trovata");
		}
		
		appelli = new ArrayList<Appello>();
		try {
			
			
			ArrayList<Corso> lista_corsi = this.getCorsiAssociati();
			
			Iterator<Corso> it = lista_corsi.iterator();
			while(it.hasNext()) {
				ArrayList<Appello> lista_app = AppelloDAO.read(it.next().getCodice());
				Iterator<Appello> it_app = lista_app.iterator();
				while(it_app.hasNext()) {
					this.appelli.add(it_app.next());
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun appello trovato");
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

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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
	
	public void mostraInfoDocente() {
		System.out.println("Docente, Nome:" + nome + " Cognome:" + cognome + " Matricola:" + matricola);
	}


	public ArrayList<Titolarita> getTitolarita() throws SQLException {

		return this.corsi_associati;
		
	}
	
	public ArrayList<Corso> getCorsiAssociati() {
		
		ArrayList<Corso> corsi = new ArrayList<Corso>();
		try {
			ArrayList<Titolarita> lista = TitolaritaDAO.readFromDocente(this.matricola);
			for (int i=0; i<lista.size(); i++) {
				corsi.add(lista.get(i).getCorso());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun corso associato.");
		}
		return corsi;
	}


	public void aggiungiTitolaritaCorso(Corso corso, int anno_accademico) throws SQLException {
		this.corsi_associati.add(new Titolarita(this.matricola, corso.getCodice(), anno_accademico));
		TitolaritaDAO.create(corso.getCodice(), this.getMatricola(), anno_accademico);
	}


	public ArrayList<Appello> getAppelli() {				
		return appelli;
	}


	public void aggiungiAppello(Appello appello, int id_appello) throws SQLException {
		this.appelli.add(appello);
		
		String sede = "";
		
		if(appello.getSede() == Sede.Aula){
			sede = "AULA";
		}else if(appello.getSede() == Sede.Laboratorio) {
			sede = "LABORATORIO";
		}else {
			sede = "ALTRO";
		}
		
		AppelloDAO.create(id_appello, appello.getData(), appello.getScadenza(), appello.getNote(), sede, appello.getCorso().getCodice(), appello.getDocente().getMatricola());
	}
	
	
}
