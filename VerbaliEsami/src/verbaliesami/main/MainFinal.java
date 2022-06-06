package verbaliesami.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import verbaliesami.boundary.BDocente;
import verbaliesami.boundary.BSegreteriaStudenti;
import verbaliesami.boundary.BStudente;

public class MainFinal {
	public static void main (String args[]) {
		System.out.println("Cosa vuoi fare?");
		System.out.println("(1) Crea appello");
		System.out.println("(2) Aggiungi docente");
		System.out.println("(3) Modifica docente");
		System.out.println("(4) Prenota appello");
		
		String pattern = "[0-9]*";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.flush();
		String input = "";
		do {
			try {
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("Input invalido");
			}
			if (!input.matches(pattern)) {
				System.out.println("Inserire un numero.");
			}
		} while (!input.matches(pattern) );
		int choice = Integer.valueOf(input);
		
		switch (choice) {
		case 1:
			BDocente.crea_appello();
			break;
		case 2:
			BSegreteriaStudenti.agg_docente();
			break;
		case 3:
			BSegreteriaStudenti.modifica_docente();
			break;
		case 4:
			BStudente.prenota_appello();
			break;
		default:
			System.out.println("Indice Errato");
			break;
		}
	}
}
