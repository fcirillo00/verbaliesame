package verbaliesami.entity;

import java.util.ArrayList;

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
		this.esaminato = new ArrayList<Valutazione>();
		
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
		esaminato.add(v);
	}
	
	public ArrayList<Valutazione> getEsaminato(){
		return this.esaminato;
	}
	

}
