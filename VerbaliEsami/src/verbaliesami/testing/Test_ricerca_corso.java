package verbaliesami.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Corso;
import verbaliesami.persistance.CorsoDAO;
import verbaliesami.persistance.DocenteDAO;
import verbaliesami.persistance.TitolaritaDAO;

class Test_ricerca_corso {

	static Corso network_security = new Corso(300, "Network Security", 6);
	static Corso reti9 = new Corso(400, "Reti di Calcolatori", 9);
	static Corso reti6 = new Corso(500, "Reti di Calcolatori", 6);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CorsoDAO.create(300, "Network Security", 6);
		CorsoDAO.create(400, "Reti di Calcolatori", 9);
		CorsoDAO.create(500, "Reti di Calcolatori", 6);
		DocenteDAO.create("Roberto", "Pietrantuono", "A00030003", "rpietrantuono", "password");
		DocenteDAO.create("Simon Pietro", "Romano", "A00030004", "spqr", "password");
		TitolaritaDAO.create(300, "A00030004", 2022);
		TitolaritaDAO.create(400, "A00030004", 2022);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		TitolaritaDAO.delete(300, "A00030004");
		TitolaritaDAO.delete(400, "A00030004");
		DocenteDAO.delete("A00030003");
		DocenteDAO.delete("A00030004");
		CorsoDAO.delete(300);
		CorsoDAO.delete(400);
		CorsoDAO.delete(500);
	}


	@Test
	void test_codiceCorso1() {
		// codice valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		Corso res = vms.ricerca_corso(300);
		
		assertEquals(res.equals(network_security), true);
	}
	
	@Test
	void test_codiceCorso2() {
		// codice non presente
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		Corso res = vms.ricerca_corso(560);
		
		assertEquals(res == null, true);
	}
	
	@Test
	void test_nomeCorso1() {
		// nome valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_denominazione("Reti di Calcolatori");
		
		boolean success = false;
		if (res.get(0).getCfu() == 6) {
			if (res.get(0).equals(reti6) && res.get(1).equals(reti9)) { success = true; }
		} else {
			if (res.get(1).equals(reti6) && res.get(0).equals(reti9)) { success = true; }
		}
		
		assertEquals(success, true);
	}
	
	@Test
	void test_nomeCorso2() {
		// nome non presente nel DB
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_denominazione("Programmazione");
		
		boolean success = res.isEmpty();

		assertEquals(success, true);
	}
	
	@Test
	void test_nomeCorso3() {
		// nome invalido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_denominazione("\'\"#\\* -- ");
		
		boolean success = res.isEmpty();

		assertEquals(success, true);
	}
	
	@Test
	void test_matricolaDocente1() {
		// matricola valida
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("A00030004");
		
		boolean success = false;
		if (res.get(0).getCfu() == 6) {
			if (res.get(0).equals(network_security) && res.get(1).equals(reti9)) { success = true; }
		} else {
			if (res.get(1).equals(network_security) && res.get(0).equals(reti9)) { success = true; }
		}

		assertEquals(success, true);
	}
	
	@Test
	void test_matricolaDocente2() {
		// matricola valida
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("A00030003");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_matricolaDocente3() {
		// matricola invalida
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("ABC");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_matricolaDocente4() {
		// matricola non presente
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("A12345678");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_nomeCognomeDocente1() {
		// nome valido, cognome valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("Simon Pietro", "Romano");
		
		boolean success = false;
		if (res.get(0).getCfu() == 6) {
			if (res.get(0).equals(network_security) && res.get(1).equals(reti9)) { success = true; }
		} else {
			if (res.get(1).equals(network_security) && res.get(0).equals(reti9)) { success = true; }
		}

		assertEquals(success, true);
	}
	
	@Test
	void test_nomeCognomeDocente2() {
		// nome valido, cognome non presente
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("Simon Pietro" , "Giggio");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_nomeCognomeDocente3() {
		// nome non presente, cognome valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("Gianluigi", "Romano");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_nomeCognomeDocente4() {
		// nome valido, cognome presente ma nome e cognome insieme non presenti
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("Simon Pietro", "Pietrantuono");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_nomeCognomeDocente5() {
		// nome non valido, cognome valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("#394034a 0=-", "Pietrantuono");
		
		assertEquals(res.isEmpty(), true);
	}
	
	@Test
	void test_nomeCognomeDocente6() {
		// nome valido, cognome non valido
		VerbaliManagementSystem vms = VerbaliManagementSystem.getInstance();
		ArrayList<Corso> res = vms.ricerca_corso_docente("Simon Pietro", "#$9834a");
		
		assertEquals(res.isEmpty(), true);
	}

}
