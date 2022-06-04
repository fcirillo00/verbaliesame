package verbaliesami.boundary;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Appello;
import verbaliesami.entity.Corso;
import verbaliesami.entity.Studente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class functions {
	public static boolean yes () throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			boolean correct_input = true;
			do {
				String ans = br.readLine();
				if (ans.compareToIgnoreCase("Y") == 0) {
					return true;
				} else if (ans.compareToIgnoreCase("N") == 0) {
					return false;
				} else {
					System.out.println("Inserisci Y o N.");
					correct_input = false;
				}
			} while (!correct_input);
			
			return false;
	}

}

public class BStudente {
	VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
	
	public static void prenota_appello() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		boolean is_corso_trovato = false;
		String pattern = "[0-9]*";
		
		Studente studente_loggato = null;
		
		Corso corso_scelto = null;
		boolean completed = false;
		try {
			do {
				System.out.println("Inserisci username: ");
				System.out.flush();
				String username = br.readLine();
				System.out.println("Inserisci password: ");
				String password = br.readLine();
				studente_loggato = vms.login(username, password);
			} while (studente_loggato == null);
		do {
			do {
				System.out.println("RICERCA CORSO");
				System.out.println("Come vuoi cercare il corso?");
				System.out.println("(1) Per nome");
				System.out.println("(2) Per codice del corso");
				System.out.println("(3) Per matricola del docente titolare");
				System.out.println("(4) Per nome e cognome del docente titolare");
				

				System.out.flush();
				String input;
				do {
					input = br.readLine();
					if (!input.matches(pattern)) {
						System.out.println("Inserire un numero.");
					}
				} while (!input.matches(pattern));
				int choice = Integer.valueOf(input);
				ArrayList<Corso> corsi = new ArrayList<Corso>();
				switch(choice) {
				case 1:
					System.out.print("Inserisci il nome: ");
					System.out.flush();
					corsi = vms.ricerca_corso_denominazione(br.readLine());
					break;
					
				case 2:
					do {
						System.out.print("Inserisci il codice del corso: ");
						System.out.flush();
						input = br.readLine();
						if (!input.matches(pattern)) {
							System.out.println("Inserire un numero.");
						}
					} while (!input.matches(pattern));
					
					int codice = Integer.valueOf(input);
					Corso c = vms.ricerca_corso(codice);
					if (c != null) {
						corsi.add(c);
					}
					break;
					
				case 3:
					System.out.print("Inserisci la matricola del docente: ");
					System.out.flush();
					String matricola;
					do {
						matricola = br.readLine();
						if (matricola.length() != 9) {
							System.out.println("Matricola deve avere una lunghezza di 9. Riprova.");
						}
					} while (matricola.length() != 9);
					
					corsi = vms.ricerca_corso_docente(matricola);
					break;
					
				case 4:
					System.out.println("Inserisci nome del docente: ");
					System.out.flush();
					String nome = br.readLine();
					System.out.println("Inserisci cognome del docente: ");
					System.out.flush();
					String cognome = br.readLine();
				
					corsi = vms.ricerca_corso_docente(nome, cognome);
					break;
					
				default:
					System.out.println("Indice errato\n");
					break;
				}
				
				if (!corsi.isEmpty()) {
					System.out.println("Corsi trovati.\n");
					// mostra corsi
					for (int i=0; i<corsi.size(); i++) {
						System.out.format("Corso (%d):\n", i+1);
						
						corsi.get(i).mostraCorso();
					}
					
					System.out.print("Hai trovato il corso che desideri? (Y/N): ");
					if (functions.yes()) {
						do {
							System.out.println("Inserisci il numero del corso: ");
							System.out.flush();
							input = br.readLine();
							if (!input.matches(pattern)) {
								System.out.println("Inserisci un numero.");
							} else if (! (Integer.valueOf(input) >= 1 && Integer.valueOf(input) <= corsi.size() )) {
								System.out.println("Inserisci un numero compreso tra quelli mostrati.");
							}
						} while (!input.matches(pattern) || !(Integer.valueOf(input) >= 1 && Integer.valueOf(input) <= corsi.size()));
						
						int scelta = Integer.valueOf(input);
						corso_scelto = corsi.get(scelta-1);
						is_corso_trovato = true;
						
					} else {
						System.out.println("Ricerca annullata. Vuoi riprovare?");
						if (!functions.yes()) {
							return;
						}
					}
				} else {
					System.out.println("Corso non trovato. Riprova. (se vuoi uscire premi Q)");
					String ans = br.readLine();
					if (ans.compareToIgnoreCase("Q") == 0) {
						return;
					}
				}
	
			} while (!is_corso_trovato);
		
		
			if (corso_scelto != null) {
				System.out.println("Hai scelto "+corso_scelto.getDenominazione());
				int codiceCorso = corso_scelto.getCodice();
				
				ArrayList<Appello> appelli = new ArrayList<Appello>();
				appelli = vms.cerca_appello(codiceCorso);

				if (!appelli.isEmpty()) {
					Appello appello_scelto = null;
					for (int i=0; i<appelli.size(); i++) {
						System.out.format("Appello (%d): \n", i+1);
						appelli.get(i).mostraInfoAppello();
					}
					
					System.out.print("Hai trovato l'appello che desideri? (Y/N): ");
					
					if (functions.yes()) {
						String input;
						do {
							do {
								System.out.println("Inserisci il numero dell'appello: ");
								System.out.flush();
								input = br.readLine();
								if (!input.matches(pattern)) {
									System.out.println("Inserisci un numero.");
								} else if (! (Integer.valueOf(input) >= 1 && Integer.valueOf(input) <= appelli.size() )) {
									System.out.println("Inserisci un numero compreso tra quelli mostrati.");
								}
							} while (! (Integer.valueOf(input) >= 1 && Integer.valueOf(input) <= appelli.size() ));
						
							int scelta = Integer.valueOf(input);
							appello_scelto = appelli.get(scelta-1);
							
							System.out.println("Hai scelto: ");
							appello_scelto.mostraInfoAppello();
							System.out.println("Vuoi confermare? (Y/N): ");
							if (functions.yes()) {
								boolean success = vms.prenota_appello(appello_scelto, studente_loggato);
								if(success) {
									
									completed = true;
									System.out.println("Ti sei prenotato con successo.");
									break;
									
								} else {
									System.out.println("Prenotazione non eseguita.");
									return;
								}
								
							} else {
								System.out.println("Prenotazione annullata. Vuoi scegliere un altro appello? (Y/N): ");
								if (!functions.yes()) {
									break;
								}
							}
						} while (true);
						
						
					} else {
						System.out.println("Ricerca annullata. Vuoi riprovare? (Y/N): ");
						if (!functions.yes()) {
							return;
						}
					}
				} else {
					System.out.println("Non ci sono appelli per il corso selezionato.");
					System.out.print("Vuoi selezionare un altro corso? ");
					if (!functions.yes()) {
						completed = true;
					}
				}
				
			} else {
				System.out.println("Errore scelta corso.");
				return;
			}
			

		} while (!completed);
	} catch (IOException e) {
			System.out.println("Input invalido");
			return;
	}
	}
}
