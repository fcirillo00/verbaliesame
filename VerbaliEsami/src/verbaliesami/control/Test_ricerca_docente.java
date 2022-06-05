package verbaliesami.control;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import verbaliesami.entity.Docente;

class Test_ricerca_docente {

	@Test
	void testRicerca_docenteStringString1() {
		
		//nome valido, cognome valido

		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		control.agg_docente("A12345678", "Giovanni", "Bianchi", "GBianchi", "");
		
		ArrayList<Docente> success = control.ricerca_docente("Giovanni", "Bianchi");
		
		control.canc_docente("A12345678");
		
		
		assertNotEquals(null, success);		
	}
	
	@Test
	void testRicerca_docenteStringString2() {
		
		//nome vuoto, cognome valido

		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		ArrayList<Docente> success = control.ricerca_docente("", "Natella");
		
		assertEquals(null, success);		
	}
	
	@Test
	void testRicerca_docenteStringString3() {
		
		//nome malformato(caratteri non lettere), cognome valido

		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		ArrayList<Docente> success = control.ricerca_docente("1234", "Natella");
		
		assertEquals(null, success);	
	}
	
	@Test
	void testRicerca_docenteStringString4() {
		
		//nome valido, cognome vuoto

		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		ArrayList<Docente> success = control.ricerca_docente("Roberto", "");
		
		assertEquals(null, success);		
	}
	
	@Test
	void testRicerca_docenteStringString5() {
		
		//nome valido, cognome malformato(caratteri non lettere)

		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		ArrayList<Docente> success = control.ricerca_docente("Roberto", "1234");
		
		assertEquals(null, success);		
	}
	
	
	

	@Test
	void testRicerca_docenteString1() {
		//matricola valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		control.agg_docente("A12345678", "Giovanni", "Bianchi", "GBianchi", "");
		
		Docente success = control.ricerca_docente("A12345678");
		
		control.canc_docente("A12345678");
		
		assertNotEquals(null, success);	
	}
	
	@Test
	void testRicerca_docenteString2() {
		//matricola non presente nel DB
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		Docente success = control.ricerca_docente("A12345678");
		
		assertEquals(null, success);		
	}
	
	@Test
	void testRicerca_docenteString3() {
		//matricola non presente nel DB
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		Docente success = control.ricerca_docente("A111");
		
		assertEquals(null, success);		
	}
	
	
	@Test
	void testRicerca_docenteString4() {
		//matricola non presente nel DB
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		Docente success = control.ricerca_docente("A111?!224");
		
		assertEquals(null, success);		
	}
	

}
