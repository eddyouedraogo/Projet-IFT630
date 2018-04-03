package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class Voies {

	/**
	 * GESTION DE L'ENTRÉE EN GARE DES TRAINS  "sur les voies "
	 * instancier les trains 
	 * gestion des semaphores
	 * exclusion mutuelles sur les regions critiques
	 */

	static final int NOMBRE_DE_VOIES = 4;
	Train[] locomotive = new Train[NOMBRE_DE_VOIES];
	private static Semaphore mutexVoies = new Semaphore(1);
	//	creation tableau de semaphore pour les 5 quais
	private static Semaphore[] semaphoreVoie = new Semaphore[NOMBRE_DE_VOIES];
	private static Semaphore entreSurVoies = new Semaphore(NOMBRE_DE_VOIES);
	

	public Voies ()
	{
		for(int i=0; i<NOMBRE_DE_VOIES; i++) {
			locomotive[i] =null;
		}

		for(int i=0; i<NOMBRE_DE_VOIES; i++) {
			semaphoreVoie[i] = new Semaphore(1);
		}

	}	



}
