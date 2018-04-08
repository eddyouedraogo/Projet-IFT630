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

	//utilisation d'un singleton
	private static Gare instance=null ;    

	public static Gare getInstance()
	{
		if (instance == null) 
			instance = new Gare();
		return instance;
	}

	private ArrayList<Train> trains = new ArrayList<Train>();
	private Quais aiguilleur;
	private Voies voies;
	private ReseauFerroviere reseauFerroviere;

	private Gare()
	{
		aiguilleur = new Quais();
		voies = new Voies();
		reseauFerroviere = new ReseauFerroviere();
	}

	public void  initGare() throws InterruptedException
	{
		/***
		 * initialiser des trains en gare a lexecution du programme
		 * initialiser des trains qui demandent a entrer en gare
		 * initilisaton de personnes sur les quais PARTIE EDDY
		 */
		//		intialisation de 5 trains en gare
		for(int k=0 ; k<5;k++)
			System.out.println("init des gares");
			nouveauTrainEnGare();
		//		intialisation de 15 trains en rotation
		for(int j=0 ; j<5;j++)
			System.out.println("init des trains en rotation");
			nouveauTrainEnRotation();
	}

    public void retirerAvion(Train trainRecu)
    {
        for (int i=0; i < trains.size()-1; i++ )
            if (trainRecu.equals(trains.get(i)))
            {
//            	trains.get(i)  delete de laffichage EDDY
            	trains.remove(i);                
            } 
    }    
    
	private Train nouveauTrainEnRotation() {
		// TODO Auto-generated method stub
		Train train = creerTrainGeneral(Train.getEtatEnRotation());
		reseauFerroviere.addTrain(train);
		return train;

	}

	private Train  nouveauTrainEnGare() {
		// TODO Auto-generated method stub
		Train train = creerTrainGeneral(Train.getEtatEnGare());
		train.surLeQuai();
		return null;
	}

	private Train creerTrainGeneral(int etat) {
		// TODO Auto-generated method stub
		String nomTrain = "LE TGV num�ro  " + trains.size() + " a �t� cr�� " ;
		System.out.println("nom du train: " + nomTrain);
		Train train = new Train(nomTrain, Train.getId(), etat);
		return train;

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
	//		String nom = "LE TGV " + idtrain + " a �t� cr�� " ;
	//		Train train = new Train(nomTrain, idtrain , capacite)
	//		return train;
	//	}




}
