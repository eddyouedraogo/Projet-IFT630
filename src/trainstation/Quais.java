package trainstation;

import java.util.ArrayList;
import java.util.concurrent.*;

import client.Voyageur;

import java.util.List;
import train.*;

public class Quais {
	/**
	 * GESTION DE L'ENTR�E EN GARE DES TRAINS  "sur les QUAIS"
	 * instancier les trains 
	 * gestion des semaphores
	 * exclusion mutuelles sur les regions critiques
	 */
	static final int NOMBRE_DE_QUAIS = 10;
	Train[] locomotive = new Train[NOMBRE_DE_QUAIS];
	private static Semaphore mutexQuais = new Semaphore(1);
	//	creation tableau de semaphore pour les 10 quais
	private static Semaphore[] semaphoreQuais = new Semaphore[NOMBRE_DE_QUAIS];
	private static Semaphore entreeQuai = new Semaphore(NOMBRE_DE_QUAIS);
	
	/**
	 * Ajout de passager sur le quais,
	 * Chaque quais a une capacite fixer
	 */
	 private static int CAPACITE = 35;
	 Voyageur[] passagers = new Voyageur[CAPACITE];
	 
	 private static Semaphore mutexPassager = new Semaphore(1);
	 private static Semaphore[] semaphorePassager = new Semaphore[CAPACITE];
	 private static Semaphore accessPassager = new Semaphore(CAPACITE);
	 
	/**
	 * instanciation d'un tableau de semaphore
	 */

	public Quais ()
	{
//		for(int i=0; i<NOMBRE_DE_QUAIS; i++) {
//			locomotive[i] =null;
//		}

		for(int i=0; i<NOMBRE_DE_QUAIS; i++) {
			semaphoreQuais[i] = new Semaphore(1);
		}
		
		for(int i=0; i<CAPACITE; i++) {
			semaphorePassager[i] = new Semaphore(1);
		}
		//Initialisation de la taille des quais
		for(int i=0; i<locomotive.length; i++) {
			locomotive[i].setSizeQuais(CAPACITE);
		}
	}	

	/**
	 * demander l'autorisation d'entrer dans un quai si l'un des 5 est disponible
	 * l'entree sur le quai se fait avec un semaphore
	 * La recherche d'un quai de libre se fait en exclusion mutuelle (ressource partag�e)
	 * @param Train train
	 * @throws InterruptedException
	 */
	public void demande_affectation_Quais (Train train) throws InterruptedException
	{
		entreeQuai.acquire();
		//rechercher un quai de disponible et de libre en exclusion mutuelle 
		mutexQuais.acquire();
		int numeroQuai = rechercheQuaiDispo();
		locomotive[numeroQuai] = train;
		mutexQuais.release();
	}

	private int rechercheQuaiDispo() {
		// TODO Auto-generated method stub
		for(int j =0; j< NOMBRE_DE_QUAIS; j++) {
			if(locomotive[j]== null)
				return j;
		}
		return 0;
	}
	/***
	 * Quitter le quai et rendre une place disponible 
	 * @param Train train aussi
	 * @throws InterruptedException
	 *  RechercheNumerodeQuai ()
	 *  release du semaphore dentree sur le quai
	 */
	public void quitterQuais (Train train) throws InterruptedException {

		int numeroQuai = rechercheQuaiAliberer(train);

		semaphoreQuais[numeroQuai].acquire();
		// aucun train n'occupe ce quai anymore
		locomotive[numeroQuai] = null;
		semaphoreQuais[numeroQuai].release();

		entreeQuai.release();
	}

	/**
	 * rechercher le quai a liberer 
	 * @param train
	 * @return
	 */
	private int rechercheQuaiAliberer(Train train) {
		// TODO Auto-generated method stub
		for(int k = 0; k<NOMBRE_DE_QUAIS;k++) {
			if(train.equals(locomotive[k]))
				return k;
		}
		return 0;
	}
	
	public void rechercheQuaisLibre(Voyageur v, int idQuais) throws InterruptedException{
		accessPassager.acquire();
		mutexPassager.acquire();
		int quaiId = getQuais();
		int placeId = ajouterPassagerAuQuai(
				locomotive[quaiId].getPassagerQuais());
		locomotive[quaiId].ajouterAuQuais(placeId, v);
		mutexPassager.release();
	}
	/**
	 * Cette fonction nous permet de rechercher un Quais dans lequel se trouve un train
	 * Et dont le quai n'est pas plein
	 * @return
	 */
	public int getQuais() {
		for(int i=0; i<NOMBRE_DE_QUAIS;i++) {
			if(locomotive[i]!=null && locomotive[i].getSizeQuais()<CAPACITE) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * On cherche une place disponible sur le quais ou on peut ajouter un train 
	 * @param vs
	 * @return
	 */
	public int ajouterPassagerAuQuai(Voyageur[] vs) {
		for(int i=0; i<vs.length; i++) {
			if(vs[i]==null) {
				return i;
			}
		}
		return 0;
	}
	
	public void passageentreTrain() {
		/**
		 * TODO
		 *  Passager quitte le quais et entre dans le train relacherla ressources 
		 */
	}
	
	public int getCapacite() {
		return CAPACITE;
	}
}
