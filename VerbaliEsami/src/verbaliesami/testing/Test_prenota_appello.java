package verbaliesami.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Appello;
import verbaliesami.entity.Studente;
import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.PrenotazioneDAO;
import verbaliesami.persistance.StudenteDAO;
//import verbaliesami.control.VerbaliManagementSystem;

class Test_prenota_appello {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// popola database
		CorsoDAO.create(300, "Ingegneria del Software", 10);
		DocenteDAO.create("Roberto", "Pietrantuono", "A00030003", "rpietrantuono", "password");
		
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 12-1, 13);
		scadenzaEsame.set(2022, 12-1, 1);
		
		AppelloDAO.create(100, dataEsame, scadenzaEsame, "Siate puntuali", "ALTRO", 300, "A00030003");
		
		dataEsame.set(2023, 1-1, 12);
		scadenzaEsame.set(2023, 1-1, 1);
		AppelloDAO.create(101, dataEsame, scadenzaEsame, "Siate ritardatari", "AULA", 300, "A00030003");
		
		StudenteDAO.create("Francesco", "Cirillo", "T46004793", "Test", "password", 12345);
		
		PrenotazioneDAO.create("T46004793", 101);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// ripulisci database
		PrenotazioneDAO.delete("T46004793", 101);
		StudenteDAO.delete("T46004793");
		AppelloDAO.delete(101);
		AppelloDAO.delete(100);
		DocenteDAO.delete("A00030003");
		CorsoDAO.delete(300);
	}
	

	@Test
	void test1() {
		// appello valido, studente valido, prenotazione non effettuata -> true
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 12-1, 13);
		scadenzaEsame.set(2022, 12-1, 1);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Siate puntuali", "ALTRO", 300, "A00030003");
		Studente s = new Studente("Francesco", "Cirillo", "T46004793", "Test", "password", 12345);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("T46004793", 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,success);
	}
	
	@Test
	void test2() {
		// appello valido solo per data, codiceCorso, matricolaDocente, studente valido, prenotazione non effettuata -> true
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 12-1, 13);
		scadenzaEsame.set(2022, 12-1, 4);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Salve", "AULA", 300, "A00030003");
		Studente s = new Studente("Francesco", "Cirillo", "T46004793", "Test", "password", 12345);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("T46004793", 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,success);
	}
	
	@Test
	void test3() {
		// appello valido, studente valido solo per matricola, prenotazione non effettuata -> true
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 12-1, 13);
		scadenzaEsame.set(2022, 12-1, 1);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Siate puntuali", "ALTRO", 300, "A00030003");
		Studente s = new Studente("Roba", "Roba", "T46004793", "Prova", "Prova", 12346);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("T46004793", 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,success);
	}
	
	@Test
	void test4() {
		// appello invalido, studente valido, prenotazione non effettuata -> false
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 3-1, 9);
		scadenzaEsame.set(2022, 1-1, 8);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Roba", "Test", 200, "A00030004");
		Studente s = new Studente("Francesco", "Cirillo", "T46004793", "Test", "password", 12345);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("T46004793", 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false,success);
	}
	
	@Test
	void test5() {
		// appello valido, studente invalido, prenotazione non effettuata -> false
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2022, 12-1, 13);
		scadenzaEsame.set(2022, 12-1, 1);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Siate puntuali", "ALTRO", 300, "A00030003");
		Studente s = new Studente("Roba", "Roba", "S46004793", "Prova", "Prova", 12346);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("S46004793", 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false,success);
	}
	
	@Test
	void test6() {
		// appello valido, studente valido, prenotazione gia effettuata -> false
		VerbaliManagementSystem vbs = VerbaliManagementSystem.getInstance();
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(2023, 1-1, 12);
		scadenzaEsame.set(2023, 1-1, 1);
		Appello a = new Appello(dataEsame, scadenzaEsame, "Siate ritardatari", "AULA", 300, "A00030003");
		Studente s = new Studente("Francesco", "Cirillo", "T46004793", "Test", "password", 12345);
		boolean success = vbs.prenota_appello(a, s);
		
		try {
			PrenotazioneDAO.delete("T46004793", 101);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false,success);
	}

}
