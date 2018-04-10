package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class Voies {

	/**
	 * GESTION DE L'ENTR�E EN GARE DES TRAINS  "sur les voies "
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
		//for(int i=0; i<NOMBRE_DE_VOIES; i++) {
		//	locomotive[i] =null;
		//}

		for(int i=0; i<NOMBRE_DE_VOIES; i++) {
			semaphoreVoie[i] = new Semaphore(1);
		}

	}
	
	public int occuperVoies(Train train) throws InterruptedException{
		entreSurVoies.acquire();
		mutexVoies.acquire();
		int voiesId = getVoiesLibre();
		locomotive[voiesId] = train;
		mutexVoies.release();
		semaphoreVoie[voiesId].acquire();
		return voiesId;
	}

	private int getVoiesLibre() 
    {
        for (int i = 0; i < NOMBRE_DE_VOIES; i++)
            if (locomotive[i] == null)
                return i;              
        return 0;
    }
	
	
	public void libereVoies(Train train) throws InterruptedException{
		for(int i=0; i<NOMBRE_DE_VOIES; i++ ) {
			if(train.equals(locomotive[i])) {
				locomotive[i] = null;
				entreSurVoies.release();
				semaphoreVoie[i].release();
				break;
			}
		}
	}

}
