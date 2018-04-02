package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class Gare {
	Semaphore sem;
	String processName;
	private int stationID;
	private int capacite;
	private String stationName;

	//singleton
	private static Gare instance=null ;    

	public static Gare getInstance()
	{
		if (instance == null) 
			instance = new Gare();
		return instance;
	}

	private ArrayList<Train> trains = new ArrayList<Train>();
	private Aiguilleur aiguilleur;
	private Voies voies;
	private GareSNCF gareSNCF;

	private Gare()
	{
		aiguilleur = new Aiguilleur();
		voies = new Voies();
		gareSNCF = new GareSNCF();
	}

	public void  initGare() throws InterruptedException
	{
		/***
		 * initialiser des trains en gare a lexecution du programme
		 * initialiser des trains qui demandent a entrer en gare
		 */
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}


	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	public Gare(Semaphore sem, String processName) {
		this.sem = sem;
		this.processName = processName;
	}


	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = (ArrayList<Train>) trains;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	//rajouter un chauffeur <
	//	initialiser liste de chauffeur 

	//	public Train TrainEnGare() throws InterruptedException
	//    {   
	//        condition avant de creer le train
	//		si le state du chauffeur n'est pas en greve alors on creer le train
	//	Train train  = creerTrainEnGare(Train.getnomTrain(), Train.getId(), capacite);
	////      train.();
	//      return train;
	//    }
	//
	//	private Train creerTrainEnGare() {
	//		// TODO Auto-generated method stub
	//		String nom = "LE TGV " + idtrain + " a été créé " ;
	//		Train train = new Train(nomTrain, idtrain , capacite)
	//		return train;
	//	}




}
