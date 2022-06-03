package verbaliesami.entity;

import java.sql.SQLException;

import verbaliesami.persistance.StudenteDAO;

public class Valutazione {
	
	private int voto;
	private String argomenti_trattati;
	
	private Verbale verbale_rif;
	private Studente esaminato;

	public Valutazione(int voto, String argomenti_trattati,Verbale verbale_rif,String matricola_studente) {
		
		this.voto = voto;
		this.argomenti_trattati = argomenti_trattati;
		this.setVerbale_rif(new Verbale(verbale_rif.getAppello_riferito()));
		try {
			this.esaminato = StudenteDAO.readUnsafe(matricola_studente);		//Uso di readUnsafe per il check del Pin
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Studente non trovato");
		}								
	}
	
	
	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getArgomenti_trattati() {
		return argomenti_trattati;
	}

	public void setArgomenti_trattati(String argomenti_trattati) {
		this.argomenti_trattati = argomenti_trattati;
	}

	public Studente getEsaminato() {
		return esaminato;
	}

	public void setEsaminato(Studente studente_esaminato) {
		this.esaminato = studente_esaminato;
	}
	
	public void setEsaminato(String matricola_studente) {
		try {
			this.esaminato = StudenteDAO.readUnsafe(matricola_studente);		//Uso di readUnsafe per il check del Pin
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Studente non trovato");
		}
	}

	
	public void mostraValutazioneStudente() {
		
		System.out.println("Valutazione Studente:");
		esaminato.mostraInfoStudente();
		System.out.println("Corso: " + this.verbale_rif.getAppello_riferito().getCorso().getDenominazione());
		System.out.println("Valutazione conseguita:");
		if(this.getVoto() == 31) {
			System.out.println("30 e Lode");
		}else {
			System.out.println(this.getVoto());
		}
		System.out.println("Argomenti trattati:");
		System.out.println(this.argomenti_trattati);
		
	}


	public Verbale getVerbale_rif() {
		return verbale_rif;
	}


	public void setVerbale_rif(Verbale verbale_rif) {
		this.verbale_rif = new Verbale(verbale_rif.getAppello_riferito());
	}
	

}
