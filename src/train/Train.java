package train;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import client.Chauffeur;
import client.Voyageur;
import main.TrainFrame;
import trainstation.Gare;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.*;

public class Train {


	/**
	 * Veuillez créer vos fonctions/ modifications  en bas du fichier svp
	 * Donner des ï¿½tats aux trains afin de mieux gerer l'exclusion mutuelle
	 */

	private static final int ETAT_EN_GARE = 1;
	static final int ETAT_EN_MARCHE = 2;
	static final int ETAT_SORTIE_DE_GARE = 3;
	static final int ETAT_ENTREE_EN_GARE = 4;
	static final int ETAT_ENTREE_EN_PANNE = 5;
	static final int ETAT_ENTREE_EN_GREVE = 6;
	
	/**
	 * Creer train
	 * Avec un chauffeur, une capacite et une liste de passagers
	 *  & **NOUVEAU** un etat
	 */
	Gare gareDeTrain;
	private int type;
	private String Destination;
	private Chauffeur chauffeur;

	private static String nomTrain;
	private static int id;
	static final int capacite = 25;
	//etat du train
	int etat;
		
		
	//Passager dans le train
	Voyageur[] passagers = new Voyageur[capacite];
	//Ajout de quais pour pouvoir positionner des passager
	Voyageur[] passagerQuais;

	/**
	 * Semaphore represantant les passagers et le chauffeur
	 * exclusion mutuelle pour gerer les places
	 */
	private static Semaphore mutexTrain = new Semaphore(1);
	private static Semaphore[] semaphoreTrain = new Semaphore[capacite];
	private static Semaphore accessTrain = new Semaphore(capacite);
	private static Semaphore semaphoreChauffeur = new Semaphore(1);

	JLabel label;
	JTextArea textArea;

	/**
	 * Gestion des passagers dans le trains
	 */

	public static  int getId() {
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

	/**
	 * Initialisation d'un Train et des semaphores representant les passagers
	 * @param nomTrain
	 * @param id
	 */
	public Train(String  nomTrain, int id, int etat) {
		this.nomTrain = nomTrain;
		this.id = id;
		setState(etat);
		gareDeTrain = Gare.getInstance();
		//label = TrainFrame.getInstance().trains(nomTrain);
//		EDDY QST
//		for(int i=0; i<capacite; i++) {
//			semaphoreTrain[i] = new Semaphore(1);
//		}
	}
	
	 @Override
	    public void finalize() {
		 	TrainFrame.getInstance().delete(label);
	    }   
	
	/**
	 * fonction qui fait entree un passager dans le train 
	 * On utilise accesTrain pour faire savoir le nombre de personne 
	 * qui pourront acceder le train
	 * On utilise l'exclusion mutuelle sur la section critique pour
	 * s'assurer que l'on a des siege disponible et on affecte le passagers
	 * @param v
	 * @throws InterruptedException
	 */
	public void entree(Voyageur v) throws InterruptedException {
		accessTrain.acquire();
		mutexTrain.acquire();
		int idSiege = placeDisponible();
		passagers[idSiege] = v;
		mutexTrain.release();
	}
	/**
	 * Permet de trouver les sieges disponible
	 * @return
	 */
	public int placeDisponible() {
		for(int i = 0; i<capacite; i++) {
			if(passagers[i] == null) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * On fait quitter le passager et
	 * On libere le siege qu'il occupe
	 * @param v
	 * @throws InterruptedException
	 */
	public void quitterTrain(Voyageur v) throws InterruptedException{
		int idSiege = sortirVoyageur(v);
		semaphoreTrain[idSiege].acquire();
		passagers[idSiege] = null;
		semaphoreTrain[idSiege].release();
		accessTrain.release();
	}

	/**
	 * Verifier si le passager est dans le train
	 * @param v
	 * @return
	 */
	public int sortirVoyageur(Voyageur v) {
		for(int i = 0; i<capacite; i++) {
			if(v.equals(passagers[i])) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Cette fonction affecte un chauffeur au train 
	 * et s'attribue la ressource jusqu'a ce que le chauffeur finisse
	 */
	public void prendreDuService(Chauffeur c){
		this.chauffeur = c;
		try {
			semaphoreChauffeur.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * On verifie si le chauffeur est bien celui qui conduit le train
	 * Si oui on arrete son service et on relache la ressource
	 * @param c
	 */
	public void serviceTerminer(Chauffeur c) {
		if(c.equals(this.chauffeur)) {
			this.chauffeur = null;
		}
		semaphoreChauffeur.release();
	}
	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public int getCapacite() {
		return capacite;
	}
	public static String  getnomTrain() {
		return nomTrain;
	}


	public static int getEtatEnGare() {
		return ETAT_EN_GARE;
	}

	/**
	 * Permet de fixer la taille du quais que l'on pourra utiliser dans la classe quais
	 * @param i
	 */

	public void setSizeQuais(int i) {
		passagerQuais = new Voyageur[i];
	}

	/**
	 * Get Taille du quais 
	 * @return
	 */
	public int getSizeQuais() {
		return passagerQuais.length;
	}

	/**
	 * On retourne la liste des passager sur le quais
	 * @return
	 */
	public Voyageur[] getPassagerQuais() {
		return passagerQuais;
	}

	/**
	 * On ajoute des passager sur le quais
	 * @param i
	 * @param v
	 */
	public void ajouterAuQuais(int i, Voyageur v) {
		passagerQuais[i] = v;
	}

	public Voyageur getVoyageurTrain(Voyageur[] vs, int i) {
		return vs[i];
	}

	public void setVoyageurTrainNull(Voyageur[] vs, int i) {
		vs[i] = null;
	}
	/***
	 * getteur et seteur de l'etat
	 */
	public int getState()
	{
		return etat;
	}

	public void setState(int nouvelEtat)
	{
		etat = nouvelEtat;
		//	        setImage(etat);
		switch (etat)
		{
		case ETAT_EN_GARE:  break;
		case ETAT_EN_MARCHE:  
			//rotation signifie que le train est en marche sur des rail a lexterieur de la gare 
			rotationTrain();
			break;
		case ETAT_SORTIE_DE_GARE:  break;
		case ETAT_ENTREE_EN_GARE:  break;                
		}        
	}  

	public static int getEtatEnRotation() {
		// TODO Auto-generated method stub
		return ETAT_EN_MARCHE;
	}
	
	/**
	 * list des fonctions detats
	 */
	public void trainEnRotationEnMarche() {
		// TODO Auto-generated method stub
		etat = ETAT_EN_MARCHE;
		rotationTrain(); //ok
	}
	public void surLeQuai() {
		// TODO Auto-generated method stub
		etat = ETAT_EN_GARE;
		trainSurQuai(); //ok
	}
	public void entreeEnGare() {
		// TODO Auto-generated method stub
		etat = ETAT_ENTREE_EN_GARE;
		entreeEnGareDuTrain();
	}


	public void sortiedeGare() throws InterruptedException {
		// TODO Auto-generated method stub
		etat = ETAT_SORTIE_DE_GARE;
		sortieDeGareTrain(); //ok
	}
	

	/**
	 * Les actions relatives aux etats et changement detat sont a ecrire ci apres
	 * 
	 */
	private void sortieDeGareTrain() throws InterruptedException{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Train thisTrain = this;
		Thread thread = new Thread()
		{
			public void run()
			{                   
				//setImage(ETAT_EN_MARCHE);                
				//Rectangle r = TrainFrame.getInstance().getBounds();               
				//Point pd = makePoint(0,0);
				//Point pf = makePoint(0,0);
				//Point p=pd;                
				int QuaisId = 0;
			
				try {
					QuaisId = gareDeTrain.voies.occuperVoies(thisTrain);
					TrainFrame.getInstance().voiesTextArea.append(nomTrain + "est sur la voie\n");
					
					Thread.sleep(1);
					
					gareDeTrain.voies.libereVoies(thisTrain);
					gareDeTrain.retirerTrain(thisTrain);
					TrainFrame.getInstance().voiesTextArea.append(nomTrain + "a liberer la voie et est sortie de la gare\n");
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//while (true)
				//{
					//if (etat != ETAT_EN_MARCHE) break;

					//if ((p.x == pf.x)&&(p.y == pf.y)){  
					///pd = makePoint(getRandom(0, r.width), r.height);
					//pf = makePoint(r.width, r.height-(r.width-pd.x));
					//p=pd;
					//}               
					//p = getNextPoint(p, pf);

					//try  {    }   catch (InterruptedException e)  {  }
					//setPosition(p);
				//}
				
				//while(true) {
					//TrainFrame.getInstance().voiesTextArea.append("Le Train "+nomTrain + "quitte la gare");
					//try  {  Thread.sleep(5);  }   catch (InterruptedException e)  {  }
				//}
				
				//try {
				//	gareDeTrain.voies.libereVoies(thisTrain);
				//	gareDeTrain.retirerTrain(thisTrain);
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
			}                       
		};
		thread.start();
		
	}
	
	private void entreeEnGareDuTrain() {
		Train thisTrain = this;
		Thread thread = new Thread() {
			public void run() {
				int voiesId = 0;
				try {
					voiesId = gareDeTrain.voies.occuperVoies(thisTrain);
					TrainFrame.getInstance().voiesTextArea.append(nomTrain+ " Occupe la voie\n");
					Thread.sleep(1); 
					
					gareDeTrain.aiguilleur.demandeEntreeSurQuai(thisTrain);
					TrainFrame.getInstance().quaisTextArea.append(nomTrain+ " Accede au Quais\n");
					Thread.sleep(1);
					
					gareDeTrain.voies.libereVoies(thisTrain);
					TrainFrame.getInstance().voiesTextArea.append(nomTrain+ " a liberer la voie\n");
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				
				//TrainFrame.getInstance().voiesTextArea.append(nomTrain+ " Occupe la voie\n");
				//try  {  Thread.sleep(5);  }   catch (InterruptedException e)  {  }
				
				//try {
				//	gareDeTrain.aiguilleur.demandeEntreeSurQuai(thisTrain);
				//}catch(InterruptedException e) {
				//	e.printStackTrace();
				//}
				
				//TrainFrame.getInstance().quaisTextArea.append(nomTrain+ " Accede au Quais\n");
				//try  {  Thread.sleep(5);  }   catch (InterruptedException e)  {  }
				
				//try {
				//	gareDeTrain.voies.libereVoies(thisTrain);
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				
				//TrainFrame.getInstance().voiesTextArea.append(nomTrain+ " a liberer la voie\n");
			}
		};
		thread.start();
	}

	private void trainSurQuai() {
		// TODO Auto-generated method stub
		/**
		 * quand le train sarrete sur le quai final
		 */
		Train locomotiveX = this;
		
		Thread thread = new Thread()
		{
			public void run()
			{                   
				try {
					gareDeTrain.aiguilleur.demandeEntreeSurQuai(locomotiveX);
					TrainFrame.getInstance().quaisTextArea.append(nomTrain + "est sur le quais\n");
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				
				//Point posTrain = getPosition();
				//Point posInQuais = gareDeTrain.aiguilleur.getTrainPoint(locomotiveX);
				
				//while (true)
				///{
					//TrainFrame.getInstance().quaisTextArea.append(nomTrain + "est sur le quais\n");
					//if ((posTrain.x == posInQuais.x)&&(posTrain.y == posInQuais.y)) break;                    
					//posTrain = getNextPoint(posTrain, posInQuais);
                    //try  {  Thread.sleep(5);  }   catch (InterruptedException e)  {  }
                    //setPosition(posTrain);
				//}
				//setImage(ETAT_EN_GARE);
			}                       
		};
		thread.start();

	}
	private void rotationTrain() {
		// TODO Auto-generated method stub
		/**
		 * 
		 */
		
		Thread thread = new Thread()
		{
			public void run()
			{                   
				//while(true) {
					//if(etat != ETAT_EN_MARCHE) break;
					TrainFrame.getInstance().rotationTextArea.append(nomTrain+" est en Marche\n");
					try  {  Thread.sleep(1);  }   catch (InterruptedException e)  {  }
				//}
                    //try  {  Thread.sleep(5);  }   catch (InterruptedException e)  {  }
			}                       
		};
		thread.start();
	}
	
	
	private Point getPosition(){
		Point p = new Point();
		p = label.getBounds().getLocation();
		return p;
	}
	
	private Point getNextPoint(Point p, Point pf)
	    {
	        if (p.x < pf.x) p.x++;
	        if (p.x > pf.x) p.x--;
	        if (p.y < pf.y) p.y++;
	        if (p.y > pf.y) p.y--;
	        return p;
	    }  
	
	private void setImage(int state) {
		String imageTrain = "/resources/TrainInStat1.png";
		switch(state) {
			case ETAT_EN_GARE:
				imageTrain = "/resources/TrainInStat1.png";
				TrainFrame.getInstance().panel.setComponentZOrder(label, 1);
				break;
			case ETAT_EN_MARCHE:
				imageTrain = "/resources/TrainInStat2.png";
				TrainFrame.getInstance().panel.setComponentZOrder(label, 1);
				break;
		}
		label.setIcon(new ImageIcon(TrainFrame.class.getResource(imageTrain)));
	}
	
    private void setPosition(Point p)
    {
        label.setBounds(p.x, p.y, (int) label.getBounds().getWidth(), (int) label.getBounds().getHeight());
    }
    
    
    private Point makePoint(int x, int y)
    {
        Point p = new Point();
        p.x = x;
        p.y = y;
        return p;
    } 
    
    private int getRandom(int min, int max)
    {
      Random r = new Random();
      return  min + r.nextInt(max-min);
    }  
}

