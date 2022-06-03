package verbaliesami.boundary;

import verbaliesami.control.VerbaliManagementSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BStudente {
	VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

	public static void prenota_appello() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("RICERCA CORSO");
		System.out.println("Come vuoi cercare il corso?");
		System.out.println("(1) Per nome");
		System.out.println("(2) Per codice del corso");
		System.out.println("(3) Per matricola del docente titolare");
		System.out.println("(4) Per nome e cognome del docente titolare");
		int choice;
		try {
			choice = br.read();
		} catch (IOException e) {
			System.out.println("Invalid input");
			return;
		}
		
		
	}
}
