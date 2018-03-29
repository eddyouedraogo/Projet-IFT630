package client;

import java.util.concurrent.*;

public class Voyageur extends Personnes implements Runnable{

	Semaphore sem;
	String processName;
	
	public Voyageur(Semaphore sem, String processName) {
		this.sem = sem;
		this.processName = processName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
