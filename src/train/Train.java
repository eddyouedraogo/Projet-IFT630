package train;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import client.Chauffeur;
import client.Voyageur;

public class Train  implements Runnable{
	
	
	/**
	 * Donner des états aux trains afin de mieux gerer l'exclusion mutuelle
	 * 
	 * 
	 */
	
    private static final int ETAT_EN_GARE = 1;
    static final int ETAT_EN_MARCHE = 2;
    static final int ETAT_SORTIE_DE_GARE = 3;
    static final int ETAT_ENTREE_EN_GARE = 4;
    static final int ETAT_ENTREE_EN_PANNE = 5;
    static final int ETAT_ENTREE_EN_GREVE = 6;
    
	
	
	
	
	/**
	 * Creer train
	 */
	
    
	
	
	
	
	
	private int type;
	private String Destination;
	private Chauffeur chauffeur;
	
	private List<Voyageur> voyageurs = new ArrayList<Voyageur>();
	Semaphore sem;
	private static String nomTrain;
	private static int id;
	private int capacite;
	
	public static  long getId() {
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

	public Train(String  nomTrain, int id , int capacite) {
		this.nomTrain = nomTrain;
		this.id = id;
		this.capacite = capacite;
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
	public static String  getnomTrain() {
		return nomTrain;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public static int getEtatEnGare() {
		return ETAT_EN_GARE;
	}

}
