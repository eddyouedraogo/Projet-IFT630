package train;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import client.Chauffeur;
import client.Voyageur;

public class Train  implements Runnable{
	
	private int id;
	private int type;
	private String Destination;
	private Chauffeur chauffeur;
	private int capacite;
	
	private List<Voyageur> voyageurs = new ArrayList<Voyageur>();
	Semaphore sem;
	String processName;
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public Train(Semaphore sem, String processName) {
		this.sem = sem;
		this.processName = processName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public List<Voyageur> getVoyageurs() {
		return voyageurs;
	}

	public void setVoyageurs(List<Voyageur> voyageurs) {
		this.voyageurs = voyageurs;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

}
