package verbaliesami.boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import verbaliesami.control.VerbaliManagementSystem;
import verbaliesami.entity.Appello;

public class BDocente {

	
	
	public static void crea_appello() {
		
		VerbaliManagementSystem control = VerbaliManagementSystem.getInstance();
		
		String id_appello = "";
		String data_giorno;
		String data_mese;
		String data_anno;
		String scadenza_giorno;
		String scadenza_mese;
		String scadenza_anno;
		String note = "";
		String sede = "";
		int codice_corso = -1;
		String matricola_docente = "";
		int anno_esame = 0;
		int mese_esame = 0;
		int giorno_esame = 0;
		int anno_scadenza = 0;
		int mese_scadenza = 0;
		int giorno_scadenza = 0;
		
		
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		String pattern = "[0-9]*";
		
		
		System.out.println("Creazione appello:");
		System.out.println("Inserire id appello: ");
		
		try {
			do {
			id_appello = buff.readLine();
			if ((Integer.valueOf(id_appello) <= 0)) {
				System.out.println("ATTENZIONE, INSERIRE UN ID_APPELLO VALIDO (>0)");
				}
			} while (Integer.valueOf(id_appello) <= 0);
		} catch (IOException e) {
			System.out.println("Errore di inserimento id");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento id");
		}
		
		
		
		try {
			do {
			System.out.println("Inserire data dell'esame anno (> 1225): ");
			data_anno = buff.readLine();
		
			if (( (!data_anno.matches(pattern)) || (data_anno.length() != 4) || Integer.valueOf(data_anno) < 1225)) {
				System.out.println("ATTENZIONE, INSERIRE UN ANNO VALIDO");
			}
			
			} while ( (!data_anno.matches(pattern)) || (data_anno.length() != 4) || Integer.valueOf(data_anno) < 1225);
			
			anno_esame = Integer.valueOf(data_anno);
			
		}  catch(IOException e) {
			System.out.println("Errore inserimento data");
		}  catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		
		
		
		
		try {
			do {
			System.out.println("Inserire data dell'esame in numeri (mese): ");
			data_mese = buff.readLine();
			if ( (!data_mese.matches(pattern)) || (Integer.valueOf(data_mese) < 1 ) || (Integer.valueOf(data_mese) > 12 ) ) {
				System.out.println("ATTENZIONE, INSERIRE UN MESE VALIDO");
			}
			} while ( (!data_mese.matches(pattern)) || (Integer.valueOf(data_mese) < 1 ) || (Integer.valueOf(data_mese) > 12 ) );
			
			mese_esame = Integer.valueOf(data_mese);
			
		} catch(IOException e) {
			System.out.println("Errore inserimento data");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		

		try {
			do {
			System.out.println("Inserire data dell'esame in numeri (giorno): ");
		
			data_giorno = buff.readLine();
			
			giorno_esame = Integer.valueOf(data_giorno);
			if ((mese_esame == 2) && giorno_esame > 28) {
				data_giorno = "x";
			} else if ( ( (mese_esame == 4) || (mese_esame == 6) || (mese_esame == 9) || (mese_esame == 11) ) && (giorno_esame > 30)) {
				data_giorno = "x";
			} else if ( (giorno_esame > 31) || (giorno_esame <= 0) ) {
				data_giorno = "x";
			}
			
			if (data_giorno.compareTo("x") == 0) {
				System.out.println("ATTENZIONE, INSERIRE UN GIORNO VALIDO");
			}
			
			} while (!data_giorno.matches(pattern));
			
		} catch(IOException e) {
			System.out.println("Errore inserimento data");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		
		
		
		try {
			do {
			System.out.println("Inserire data della scadenza anno (> 1225): ");
			scadenza_anno = buff.readLine();
			if ( (!scadenza_anno.matches(pattern)) || (scadenza_anno.length() != 4) || (Integer.valueOf(scadenza_anno) < 1225)) {
				System.out.println("ATTENZIONE, INSERIRE UN ANNO VALIDO");
			}
			} while ( (!scadenza_anno.matches(pattern)) || (scadenza_anno.length() != 4) || (Integer.valueOf(scadenza_anno) < 1225));
			
			anno_scadenza = Integer.valueOf(scadenza_anno);
			
		}  catch(IOException e) {
			System.out.println("Errore inserimento data");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		
		
		
		
		try {
			do {
			System.out.println("Inserire data della scadenza in numeri (mese): ");
			scadenza_mese = buff.readLine();
			if ((!scadenza_mese.matches(pattern)) || (Integer.valueOf(scadenza_mese) < 1 ) || (Integer.valueOf(scadenza_mese) > 12 )) {
				System.out.println("ATTENZIONE, INSERIRE UN MESE VALIDO");
			}
			} while ( (!scadenza_mese.matches(pattern)) || (Integer.valueOf(scadenza_mese) < 1 ) || (Integer.valueOf(scadenza_mese) > 12 ) );
			
			mese_scadenza = Integer.valueOf(scadenza_mese);
			
		} catch(IOException e) {
			System.out.println("Errore inserimento data");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		

		try {
			do {
			System.out.println("Inserire data dell'esame in numeri (giorno): "); 
		
			scadenza_giorno = buff.readLine();
			
			giorno_scadenza = Integer.valueOf(scadenza_giorno);
			if ((mese_scadenza == 2) && giorno_scadenza > 28) {
				scadenza_giorno = "x";
			} else if ( ( (mese_scadenza == 4) || (mese_scadenza == 6) || (mese_scadenza == 9) || (mese_scadenza == 11) ) && (giorno_scadenza > 30)) {
				scadenza_giorno = "x";
			} else if ( (giorno_esame > 31) || (giorno_esame < 0) ) {
				scadenza_giorno = "x";
			}
			
			if ((scadenza_giorno.compareTo("x") == 0) || (!scadenza_giorno.matches(pattern))) {
				System.out.println("ATTENZIONE, INSERIRE UN GIORNO VALIDO");
			}
			
			} while (!scadenza_giorno.matches(pattern));
			
		} catch(IOException e) {
			System.out.println("Errore inserimento data");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento data");
		}
		
		
		System.out.println("Inserire note corso: ");
		try {
			note = buff.readLine();
		} catch(IOException e) {
			System.out.println("Errore inserimento note");
		}
		
		
		try {
			do {
			System.out.println("Inserire sede del corso (AULA = 1), (LABORATORIO = 2), (ALTRO = 3)");
			String buffer = buff.readLine();
			if (buffer.compareTo("1") == 0) {
				sede = "AULA";
			} else if (buffer.compareTo("2") == 0) {
				sede = "LABORATORIO";
			} else if (buffer.compareTo("3") == 0) {
				sede = "ALTRO";
			} else {
				sede = "ERRORE";
			}
			
			if(sede.compareTo("ERRORE") == 0) {
				System.out.println("ATTENZIONE, INSERIRE SEDE VALIDA");
			}
			
			} while ((sede.compareTo("ERRORE") == 0 ));
		} catch(IOException e) {
			System.out.println("Errore inserimento sede");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento sede");
		}
		
		
		System.out.println("Inserire codice corso: ");
		try {
			String temp = buff.readLine();
			codice_corso = Integer.valueOf(temp);
		} catch(IOException e) {
			System.out.println("Errore inserimento codice");
		} catch (NumberFormatException n) {
			System.out.println("Errore di inserimento codice");
		}
		
		
		try {
			do {
			System.out.println("Inserire matricola docente (9 caratteri): ");
			matricola_docente = buff.readLine();
			if (matricola_docente.length() != 9) {
				System.out.println("ATTENZIONE, INSERIRE MATRICOLA DI 9 CARATTERI");
			}
			} while (matricola_docente.length() != 9);
		} catch(IOException e) {
			System.out.println("Errore inserimento matricola");
		}
		
		
		Calendar dataEsame = new GregorianCalendar();
		Calendar scadenzaEsame = new GregorianCalendar();
		
		dataEsame.set(anno_esame, mese_esame-1, giorno_esame);
		scadenzaEsame.set(anno_scadenza, mese_scadenza-1, giorno_scadenza);
		if (scadenzaEsame.after(dataEsame)) {
			scadenzaEsame.setTimeInMillis(dataEsame.getTimeInMillis() - 86400000);
		}
		
		Appello a = new Appello(dataEsame, scadenzaEsame, note, sede, codice_corso, matricola_docente);
		
		control.crea_appello(a, Integer.valueOf(id_appello));
		
	}
	
}
