package client;
import java.util.concurrent.*;

public class Chauffeur extends Personnes implements Runnable{
	
	private boolean isOnStrike;
	Semaphore sem;
	String processName;
	
	public Chauffeur(Semaphore sem, String processName){
		this.sem = sem;
		this.processName = processName;
		
	}

	public boolean isOnStrike() {
		return isOnStrike;
	}
	public void setOnStrike(boolean isOnStrike) {
		this.isOnStrike = isOnStrike;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}