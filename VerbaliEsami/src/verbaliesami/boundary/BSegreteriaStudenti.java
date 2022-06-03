package verbaliesami.boundary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import verbaliesami.control.VerbaliManagementSystem;

public class BSegreteriaStudenti {

	VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
	
	public void agg_docente() {
		
		String nome_doc = "";
		String cognome_doc = "";
		String matricola_doc = "";
		String username_doc = "";
		String password_doc = "";
		

		System.out.println("INSERIMENTO DOCENTE");
		System.out.println("INSERIRE NOME DOCENTE:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			nome_doc = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Nome non valido");
			return;
		}
		
		System.out.println("INSERIRE COGNOME DOCENTE:"); 
		
		try {
			cognome_doc = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Cognome non valido");
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
		} 
		
		System.out.println("INSERIRE PASSWORD DOCENTE:");
	
		try {
			password_doc = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Password non valida");
		} 
		
		control.agg_docente(matricola_doc, nome_doc, cognome_doc, username_doc, password_doc);
		
	}
	
	public void modifica_docente() {
		
	
	}
	
	public void agg_corso() {
		
	}
	
	public void modifica_corso() {
		
	}
	
	public void associa_docente_corso() {
		
		
		
	}

	
}
