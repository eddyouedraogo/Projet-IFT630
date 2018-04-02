package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;
import train.*;

public class Gare implements Runnable{
	Semaphore sem;
	String processName;
	private int stationID;
	private int capacite;
	private String stationName;

	//singleton
	private static Gare instance=null ;    
	
	/*public static Gare getInstance()
	{
		if (instance == null) 
		instance = new Aeroport();
		return instance;
	}*/
	
	private ArrayList<Train> trains = new ArrayList<Train>();

    private Gare()
    {
        Aiguilleur aiguilleur = new Aiguilleur();
        Voies voies = new Voies();
        GareSNCF gareSNCF = new GareSNCF();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub

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

}
