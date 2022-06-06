package verbaliesami.entity;

import java.sql.SQLException;
import java.util.ArrayList;

import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.ValutazioneDAO;
import verbaliesami.persistance.VerbaleDAO;

public class Verbale {
	
	private Appello appello_riferito;
	private ArrayList<Valutazione> esaminato;
	private Docente esaminatore;
	
	public Verbale(Appello appello_riferito) {
		
		this.appello_riferito = new Appello(appello_riferito);
		this.esaminatore = new Docente(appello_riferito.getDocente());
		this.esaminato = new ArrayList<Valutazione>();
		
	}
	
	public Verbale(Verbale verbale) {
		
		this.appello_riferito = new Appello(verbale.getAppello_riferito());
		this.esaminatore = new Docente(verbale.getEsaminatore());
		try {
			this.esaminato = new ArrayList<Valutazione>(ValutazioneDAO.readValutazioni(VerbaleDAO.readIdFromAppello(AppelloDAO.readId(this.appello_riferito))));
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nessun appello o nessuna valutazione trovata");
		}
		
	}
	
	
	public Appello getAppello_riferito() {
		return appello_riferito;
	}
	public void setAppello_riferito(Appello appello_riferito) {
		this.appello_riferito = new Appello(appello_riferito);
	}
	public Docente getEsaminatore() {
		return esaminatore;
	}
	public void setEsaminatore(Docente esaminatore) {
		this.esaminatore = esaminatore;
	}
	
	public void aggiungiEsaminato(Valutazione v) {
		//La valutazione deve essere già inserita nel DB
		esaminato.add(v);
	}
	
	public ArrayList<Valutazione> getEsaminato() {
		
		return this.esaminato;
	}
	

}
