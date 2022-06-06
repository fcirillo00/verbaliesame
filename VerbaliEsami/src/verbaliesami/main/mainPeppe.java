package verbaliesami.main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.boundary.BDocente;
import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Appello;
import verbaliesami.persistance.AppelloDAO;


public class mainPeppe {
	
		public static void main (String args[]) {
			BDocente.crea_appello();
			/*VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
			Calendar data = new GregorianCalendar();
			Calendar scadenza = new GregorianCalendar();
			data.set(2022,14, 31);
			scadenza.set(2022, 3, 3);
			
			Appello a = new Appello(data, scadenza, "Ciao", "Aula", 2, "A00000001");
			int id = 15;
			control.crea_appello(a, id);
			
			try {
				AppelloDAO.delete(id);
				} catch (SQLException e) {
					System.out.println("Eccezione");
				}*/
		}
	

}
