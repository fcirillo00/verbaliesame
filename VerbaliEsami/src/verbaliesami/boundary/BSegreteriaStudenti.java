package verbaliesami.boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Corso;
import verbaliesami.entity.Docente;

public class BSegreteriaStudenti {

	

	public static void agg_docente() {
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		String pattern = "[a-zA-Z]*";
		
		String nome_doc = "";
		String cognome_doc = "";
		String matricola_doc = "";
		String username_doc = "";
		String password_doc = "";
		
		System.out.println("INSERIMENTO DOCENTE");
		System.out.println("INSERIRE NOME DOCENTE:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		try {
			
			do {
				nome_doc = br.readLine();
				if(!nome_doc.matches(pattern)) {
					System.out.println("Hai inserito numeri. Riprovare:");
				}
			}while(!nome_doc.matches(pattern));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nome non valido");
			return;
		}
		
		System.out.println("INSERIRE COGNOME DOCENTE:"); 
		
		try {
			do {
				cognome_doc = br.readLine();
				if(!cognome_doc.matches(pattern)) {
					System.out.println("Hai inserito numeri. Riprovare:");
				}
			}while(!cognome_doc.matches(pattern));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Cognome non valido");
			return;
		} 
		
		System.out.println("INSERIRE MATRICOLA DOCENTE:"); 
		
		try {
			
			while(matricola_doc.length() != 9) {
				matricola_doc = br.readLine();
				
				if(matricola_doc.length() != 9) {
					System.out.println("Inserita matricola non corretta, riprovare...");
				}
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Matricola non valida");
		} 
		
		System.out.println("INSERIRE USERNAME DOCENTE:");
		
		try {
			username_doc = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Username non valido");
			return;
		} 
		
		System.out.println("INSERIRE PASSWORD DOCENTE:");
	
		try {
			password_doc = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Password non valida");
			return;
		} 
		
		control.agg_docente(matricola_doc, nome_doc, cognome_doc, username_doc, password_doc);
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ERRORS HAPPENS");
			return;
		}
		
	}
	
	public static void modifica_docente() {	
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		Docente doc;
		
		System.out.println("Ricerca per [nome,cognome] o [matricola]?");
		System.out.println("\tInserire 0 per [nome,cognome]");
		System.out.println("\tInserire 1 per [matricola]");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String buffer = "";
		String pattern = "[a-zA-Z]*";
		String pattern2 = "[' ']";
		
		do {
			
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Inserimento non valido");
				return;
			}
			
			if((buffer.compareTo("0") != 0)&&(buffer.compareTo("1") != 0)) {
				System.out.println("Valore inserito non valido. Reinserire...");
			}
						
		}while((buffer.compareTo("0") != 0)&&(buffer.compareTo("1") != 0));
		
		if(buffer.compareTo("0") == 0) {
			
			String nome_doc = "";
			String cognome_doc = "";
			
			try {
				
				System.out.println("INSERIRE NOME DOCENTE:");
				
				do {
					nome_doc = br.readLine();
					if(!nome_doc.matches(pattern)) {
						System.out.println("Hai inserito caratteri non validi. Riprovare:");
					}
				}while((!nome_doc.matches(pattern)) && (!nome_doc.matches(pattern2)));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Nome non valido");
				return;
			}
			
			System.out.println("INSERIRE COGNOME DOCENTE:"); 
			
			try {
				do {
					cognome_doc = br.readLine();
					if(!cognome_doc.matches(pattern)) {
						System.out.println("Hai inserito caratteri non validi. Riprovare:");
					}
				}while(!cognome_doc.matches(pattern));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Cognome non valido");
				return;
			} 
			
			ArrayList<Docente> d = new ArrayList<Docente>(control.ricerca_docente(nome_doc, cognome_doc));
			
			if(d.isEmpty()) {
				System.out.println("Nessun docente trovato");
				return;
			}
			
			Iterator<Docente> it = d.iterator();
			int indice_docente = 1;
			
			while(it.hasNext()) {
				Docente docente = new Docente(it.next());
				System.out.println("[" + indice_docente + "] " + docente.getCognome() + " " + docente.getNome() + " " + docente.getMatricola());
				ArrayList<Corso> c = new ArrayList<Corso>(docente.getCorsiAssociati());
				Iterator<Corso> it_corso = c.iterator();
				System.out.println("Corsi associati:");
				while(it.hasNext()) {
					System.out.println(it_corso.next().getDenominazione());
				}
				
				indice_docente++;					
			}
			
			int indice = 0;
			System.out.println("Inserire indice docente da selezionare: ");
			
			do {
				try {
					buffer = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Inserimento non valido");
					return;
				}
				
				indice = Integer.valueOf(buffer);
				
				if((indice < 0 && indice > indice_docente)) {
					System.out.println("Valore non valido. Riprovare");
				}
				
			}while(indice < 0 && indice > indice_docente);
			
			doc = new Docente(d.get(indice-1));
			
		}else {
			
			String matricola_doc = "";
			
			System.out.println("INSERIRE MATRICOLA DOCENTE:"); 
			
			try {
				
				while(matricola_doc.length() != 9) {
					matricola_doc = br.readLine();
					
					if(matricola_doc.length() != 9) {
						System.out.println("Inserita matricola non corretta, riprovare...");
					}
				}			
			}catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Matricola non valida");
				return;
			} 
			
			doc = new Docente(control.ricerca_docente(matricola_doc));
			
			if(doc.equals(null)) {
				System.out.println("Nessun docente trovato");
				return;
			}
		}
		
		
		Docente new_docente = new Docente(doc);
		
		control.canc_docente(doc.getMatricola());
		
		System.out.println("Modificare il nome?");
		System.out.println("Inserire Y come si, N come no");
		
		do {
			
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Inserimento non valido");
				return;
			}
			
			if((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0)) {
				System.out.println("Valore inserito non valido. Reinserire...");
			}
						
		}while((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0));
		
		if(buffer.compareTo("Y")==0) {
			String nome_doc = "";
			try {
				
				System.out.println("INSERIRE NOME DOCENTE:");
				
				do {
					nome_doc = br.readLine();
					if(!nome_doc.matches(pattern)) {
						System.out.println("Hai inserito caratteri non validi. Riprovare:");
					}
				}while(!nome_doc.matches(pattern));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Nome non valido");
				return;
			}
			
			
			new_docente.setNome(nome_doc);
		}
		
		System.out.println("Modificare il cognome?");
		System.out.println("Inserire Y come si, N come no");
		
		do {
			
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Inserimento non valido");
				return;
			}
			
			if((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0)) {
				System.out.println("Valore inserito non valido. Reinserire...");
			}
						
		}while((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0));
		
		if(buffer.compareTo("Y")==0) {
			String cognome_doc = "";
			try {
				
				System.out.println("INSERIRE COGNOME DOCENTE:");
				
				do {
					cognome_doc = br.readLine();
					if(!cognome_doc.matches(pattern)) {
						System.out.println("Hai inserito caratteri non validi. Riprovare:");
					}
				}while(!cognome_doc.matches(pattern));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Nome non valido");
				return;
			}
			
			
			new_docente.setCognome(cognome_doc);
		}
		
		System.out.println("Modificare la matricola?");
		System.out.println("Inserire Y come si, N come no");
		
		do {
			
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Inserimento non valido");
				return;
			}
			
			if((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0)) {
				System.out.println("Valore inserito non valido. Reinserire...");
			}
						
		}while((buffer.compareTo("Y") != 0)&&(buffer.compareTo("N") != 0));
		
		if(buffer.compareTo("Y")==0) {
			String matricola_doc = "";
			try {
				
				System.out.println("INSERIRE MATRICOLA DOCENTE:");
				
				while(matricola_doc.length() != 9) {
					matricola_doc = br.readLine();
					
					if(matricola_doc.length() != 9) {
						System.out.println("Inserita matricola non corretta, riprovare...");
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Nome non valido");
				return;
			}
			
			
			new_docente.setMatricola(matricola_doc);
		}
		
		control.agg_docente(new_docente);
		
		System.out.println("Docente modificato");
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ERRORS HAPPENS");
			return;
		}		
	}
	
	public static void agg_corso() {
		
	}
	
	public static void modifica_corso() {
		
	}
	
	public static void associa_docente_corso() {
	
	}

	
}
