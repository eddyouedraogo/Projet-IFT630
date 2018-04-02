package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class GareDeLyon {

	
	private ArrayList<Train> trains = new ArrayList<Train>();
    private static Semaphore mutex = new Semaphore(1);
    
	/**
	 * GESTION DE LA GARE ET DES SEMAPHORES
	 * ajout d'un train
	 * get un train 
	 * retirer un train de la gare de lyon
	 */
}
