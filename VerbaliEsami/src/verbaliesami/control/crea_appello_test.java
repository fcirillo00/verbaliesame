package verbaliesami.control;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.persistance.AppelloDAO;
import verbaliesami.persistance.CorsoDAO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import verbaliesami.entity.Appello;

class crea_appello_test {
	
	Appello a = null;
	int id = 0;
	Calendar data = new GregorianCalendar();
	Calendar scadenza = new GregorianCalendar();

	@BeforeAll
	static void SetUpBeforeClass () throws Exception{
	CorsoDAO.create(2, "CorsoDiProva", 10);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception{
	CorsoDAO.delete(2);
	}
	
	@Test
	void crea_appello_1() {
		// tutto valido
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 11, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A00000001");
		id = 5;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}
		assertEquals(true, success);
		
	}
	
	@Test
	void crea_appello_2(){
		//data non valida
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(1005, 12, 5);
		scadenza.set(2022, 11, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A00000001");
		id = 6;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}
		
		assertEquals(false, success);
	}
	
	@Test
	void crea_appello_3(){
		//scadenza non valida
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(1005, 11, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A00000001");
		id = 7;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}
	
	@Test
	void crea_appello_4(){
		//scadenza valida ma successiva
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 10);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A00000001");
		id = 8;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(true, success);
	}
	
	@Test
	void crea_appello_5(){
		//codice corso non valido
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 987654, "A00000001");
		id = 9;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}
	
	@Test
	void crea_appello_6(){
		//matricola non valida (!= 9 caratteri)
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A00000001A");
		id = 10;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}
	
	@Test
	void crea_appello_7(){
		//matricola non valida (non esistente nel db)
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A0000000B");
		id = 11;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}	
	
	@Test
	void crea_appello_8(){
		//matricola non valida (presenza di caratteri speciali)
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A!!@aaa");
		id = 12;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}	
	
	@Test
	void crea_appello_9(){
		//id appello non valido perchè minore o uguale a zero
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();

		data.set(2022, 12, 5);
		scadenza.set(2022, 12, 3);
		
		a = new Appello(data, scadenza, "Ciao", "1", 2, "A!!@aaa");
		id = -1;
		
		boolean success = control.crea_appello(a, id);
		try {
		AppelloDAO.delete(id);
		} catch (SQLException e) {
			System.out.println("Eccezione");
		}

		assertEquals(false, success);
	}	
	

}
