package verbaliesami.boundary;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Corso;
import verbaliesami.persistance.CorsoDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;


public class BStudente {
	VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
	
	public static void prenota_appello() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean corso_trovato = false;
		String pattern = "[0-9]*";

		do {
			System.out.println("RICERCA CORSO");
			System.out.println("Come vuoi cercare il corso?");
			System.out.println("(1) Per nome");
			System.out.println("(2) Per codice del corso");
			System.out.println("(3) Per matricola del docente titolare");
			System.out.println("(4) Per nome e cognome del docente titolare");
			
			try {
				System.out.flush();
				int choice = Integer.valueOf(br.readLine());
				ArrayList<Corso> corsi = new ArrayList<Corso>();
				switch(choice) {
				case 1:
					System.out.print("Inserisci il nome: ");
					System.out.flush();
					try {
					corsi = CorsoDAO.read(br.readLine());
					} catch (SQLException e) {
						System.out.println("Errore nel DB.");
					}
					break;
				case 2:
					try {
						String input;
						do {
							System.out.print("Inserisci il codice del corso: ");
							System.out.flush();
							input = br.readLine();
							if (!input.matches(pattern)) {
								System.out.println("Inserire un numero.");
							}
						} while (!input.matches(pattern));
						
						int codice = Integer.valueOf(input);
						Corso c = CorsoDAO.read(codice);
						if (c != null) {
							corsi.add(c);
						}
					} catch (SQLException e) {
						System.out.println("Errore nel DB");
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
					
					try {
						corsi = CorsoDAO.readFromMatricola(matricola);
					} catch (SQLException e) {
						System.out.println("Errore nel DB");
					}
					break;
				case 4:
					System.out.println("Inserisci nome del docente: ");
					System.out.flush();
					String nome = br.readLine();
					System.out.println("Inserisci cognome del docente: ");
					System.out.flush();
					String cognome = br.readLine();
					try {					
						corsi = CorsoDAO.readFromNomeCognome(nome, cognome);
					} catch (SQLException e) {
						System.out.println("Errore nel DB");
					}
					break;
				default:
					System.out.println("Indice errato\n");
					break;
				}
				
				if (!corsi.isEmpty()) {
					corso_trovato = true;
					System.out.println("Corsi trovati.");
					System.out.println(corsi.size());
					System.out.println(corsi.get(0).getDenominazione());
				} else {
					System.out.println("Corso non trovato. Riprova.");
				}

			} catch (IOException e) {
				System.out.println("Input invalido");
				return;
			}
		} while (!corso_trovato);
	}
}
