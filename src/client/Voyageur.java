package client;

import java.util.concurrent.*;

public class Voyageur extends Personnes{

	Semaphore sem;
	String processName;
	
	public Voyageur(Semaphore sem, String processName) {
		this.sem = sem;
		this.processName = processName;
	}


}
