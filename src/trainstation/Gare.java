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
	
	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	private String stationName;
	
	
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	private List<Train> trains = new ArrayList<Train>();
	
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
		this.trains = trains;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

}
