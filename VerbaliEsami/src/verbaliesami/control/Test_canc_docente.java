package verbaliesami.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_canc_docente {

	@Test
	void testCanc_docente1() {
		//matricola valida
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		control.agg_docente("A12345678", "Test", "Prova", "", "");
		
		boolean success = control.canc_docente("A12345678");
		
		assertEquals(true, success);
		
	}
	
	@Test
	void testCanc_docente2() {
		//matricola non presente nel DB
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.canc_docente("A12345678");
		
		assertEquals(false, success);
		
	}
	
	@Test
	void testCanc_docente3() {
		//matricola malformata(!= 9 caratteri)
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.canc_docente("A123");
		
		assertEquals(false, success);
		
	}
	
	@Test
	void testCanc_docente4() {
		//matricola malformata(!= 9 caratteri)
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		boolean success = control.canc_docente("A1234567?");
		
		assertEquals(false, success);
	}
	
	
	

}
