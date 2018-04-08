package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class ReseauFerroviere {


	private ArrayList<Train> trains = new ArrayList<Train>();
	private static Semaphore mutex = new Semaphore(1);
	/**
	 * GESTION DE LA GARE ET DES SEMAPHORES
	 * ajout d'un train
	 * get un train 
	 * retirer un train de la gare de lyon
	 * Veuillez vous attribuer cette classe et y aposer votre nom svp
	 * Aziz
	 * l'ajout d'un train devra se faire en exclusion mutuelle 
	 * la supression dun train devra ...se faire en exclusion mutuelle 
	 */

	public void addTrain(Train train) {
		// TODO Auto-generated method stub
		// ajouter un train a la liste des train sur le reseau ferroviere 
		try {
			mutex.acquire();
			trains.add(train);
			mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeTrain(Train trainRecu) {
		// TODO Auto-generated method stub
		// ajouter un train a la liste des train sur le reseau ferroviere 
		try {
			mutex.acquire();
			//			trains.add(train);
			//			parcourir le array pour trouver quel index de train a l'id recherché a éliminer
			for(int k=0; k<trains.size()-1;k++) {
				if(trainRecu.equals(trains.get(k))){
					trains.remove(k); 
					//					sortie de boucle
					break;
				}
			}
			mutex.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * generation automatique de  geteur et seteurs
	 */


	public void setTrains(ArrayList<Train> trains) {
		this.trains = trains;
	}
	public static Semaphore getMutex() {
		return mutex;
	}
	public static void setMutex(Semaphore mutex) {
		ReseauFerroviere.mutex = mutex;
	}

	public Train getTrain() {
		if(trains.size()>0)
			return trains.get(0); // retourner lindex zero
		else
			return null;

	}



}
