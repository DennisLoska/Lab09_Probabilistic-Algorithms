package philosophersProblem;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * Die Philosohen werden nach einer zufälligen zeit hungrig und essen auch zufällig
 *  Lang in bestimmten grenzen - die Änderungen der Zustände werden mit systemoutprints
 *  dargestellt;
 */

public class Philosopher implements Runnable{
	private String name;
	int seatNumber;
	boolean hasLeftFork = false;
	boolean hasRightFork = false;
	
	
	public Philosopher(String name, int seatNumber){
		this.name = name;
		this.seatNumber = seatNumber;
	}
	
	public void run(){
		boolean printedLeft = false;
		boolean printedRight = false;
		while(true){
		if(checkLeftFork()){
			takeLeftFork();
			hasLeftFork = true;
		}else{
			if(!printedLeft){
				System.out.println(seatNumber +name + " waits for the leftFork");
				printedLeft = true;
			}
		}
		if(checkRightFork()){
			takeRightFork();
			hasRightFork = true;
		}else{
			if(!printedRight){
				System.out.println(seatNumber +name + " waits for the rightFork");
				printedRight = true;
			}
		}
		
		if(hasRightFork&&hasLeftFork){
			eat();
			printedLeft = false;
			printedRight = false;
		}
			
		}
		//delights himself with the delicate dish
		
	}
	public synchronized void eat(){
		System.out.println(seatNumber +name + " eats");
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(seatNumber + name + " has finished eating");
		putDownLeftFork();
		System.out.println(seatNumber +name + " puts down the left fork");
		putDownRightFork();
		System.out.println(seatNumber +name + " puts down the right fork");
	}
	
	public boolean checkLeftFork(){
		if(PhilosophersTable.getForkArray()[(seatNumber-1)]==1)return true;
		else return false;
	}
	public boolean checkRightFork(){
		if(PhilosophersTable.getForkArray()[(seatNumber)%5]==1)return true;
		else return false;
	}
	public void takeLeftFork(){
		PhilosophersTable.setForkArray(seatNumber-1, 0);
	}
	public void takeRightFork(){
		PhilosophersTable.setForkArray(seatNumber%5,0);
	}
	public void putDownRightFork(){
		PhilosophersTable.setForkArray(seatNumber%5,1);
	}
	public void putDownLeftFork(){
		PhilosophersTable.setForkArray(seatNumber-1, 1);
	}
	
	/*
	  //threadcode //Lebenscode eines philosophers
	
	while(true){
		//getting hungry
		if(!countedTimeToGetHungry){
		timeUntilHungry = (randomer.nextInt(126)+5)*1000;
		System.out.println(timeUntilHungry);
		countedTimeToGetHungry = true;
		System.out.println(name + " is thinking");
		}else{
			//eat
			if(System.currentTimeMillis()-timeSinceLastEaten==timeUntilHungry){
				System.out.println(name + " is hungry;");
				if(checkLeftFork(seatNumber)){
					takeLeftFork(seatNumber);
					System.out.println(name + " takes the left fork");
				}else{
					waitingForLeftFork=true;
					System.out.println(name + " is waiting for the left fork");
				}
				if(checkRightFork(seatNumber)){
					takeRightFork(seatNumber);
					System.out.println(name + " takes the right fork");
				}else{
					waitingForRightFork=true;
					System.out.println(name + " is waiting for the right fork");
				}
			}
		}
	}
	 */
}
