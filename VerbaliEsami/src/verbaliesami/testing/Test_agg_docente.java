package verbaliesami.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Docente;

class Test_agg_docente {

	@Test
	void testAgg_docenteDocente1() {
		
		//matricola valida, nome valido, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Francesco","Gialli","A00113355", "FGialli", "1234"));		

		control.canc_docente("A00113355");
		
		assertEquals(true,success);
	}
	
	@Test
	void testAgg_docenteDocente2() {
		
		//matricola presente nel DB, nome valido, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "Rossi", "A00000001", "GioRossi33", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente3() {
		
		//matricola malformata(!= 9 caratteri), nome valido, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "Rossi", "N460034", "GioRossi45", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente4() {
		
		//matricola malformata(caratteri non alfanumerici), nome valido, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "Rossi", "N4600340?", "GRossi", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente5() {
		
		//matricola valida, nome malformato(caratteri non lettere), cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni1", "Rossi", "A00110011", "GXRossi", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente6() {
		
		//matricola valida, nome vuoto, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("", "Rossi", "A00110033", "GioXRossi", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente7() {
		
		//matricola valida, nome valido, cognome malformato(caratteri non lettere), username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Mattia", "Bianchi1", "A00110055", "GioMagenta", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente8() {
		
		//matricola valida, nome valido, cognome vuoto, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "", "A00110077", "GioBlu", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente9() {
		
		//matricola valida, nome valido, cognome vuoto, username invalido(gi? presente nel DB), password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "", "A00110077", "rnatella", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente10() {
		
		//matricola valida, nome valido, cognome valido, username invalido (vuoto), password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovanni", "Verdi", "B00110000", " ", "1234"));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente11() {
		
		//matricola valida, nome valido, cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Giovannino", "Rossi", "C11001100", "RossiGio", ""));		
		
		control.canc_docente("C11001100");
		assertEquals(true,success);
	}
	
	@Test
	void testAgg_docenteDocente12() {
		
		//matricola valida, nome malformato(>50 caratteri), cognome valido, username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Rossi", "C11001100", "RossiGio", ""));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente13() {
		
		//matricola valida, nome valido, cognome malformato(>50 caratteri), username valido, password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Roberto", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "C11001100", "RossiGio", ""));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente14() {
		
		//matricola valida, nome valido, cognome valido, username malformato(>50 caratteri), password valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Roberto", "Gazza", "C11001100", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ""));		
		
		assertEquals(false,success);
	}
	
	@Test
	void testAgg_docenteDocente15() {
		
		//matricola valida, nome valido, cognome valido, username valido, password malformata(>50 caratteri)
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.agg_docente(new Docente("Roberto", "Gazza", "C11001100", "RGazza", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));		
		
		assertEquals(false,success);
	}
	
	

}
