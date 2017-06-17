package philosophersProblem;

import java.lang.Thread.State;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
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
	private State state;
	
	
	public Philosopher(String name, int seatNumber){
		this.name = name;
		this.seatNumber = seatNumber;
	}
	
	public void run(){
		while(true){
			synchronized(this){
		    	if(checkLeftFork()){
					takeLeftFork();
					hasLeftFork = true;
				}else{
						System.out.println(seatNumber +name + " waits for the leftFork");	
				}
				if(checkRightFork()){
					takeRightFork();
					hasRightFork = true;
				}else{
						System.out.println(seatNumber +name + " waits for the rightFork");
				}
				
				if(hasRightFork&&hasLeftFork){
					eat();
				}else{
					try {
						wait(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
		//delights himself with the delicate dish
	public void eat(){
		System.out.println(seatNumber +name + " eats");
		try {
			wait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(seatNumber + name + " has finished eating");
		putDownLeftFork();
		//System.out.println(seatNumber +name + " puts down the left fork");
		putDownRightFork();
		//System.out.println(seatNumber +name + " puts down the right fork");
	}
	
	/*private void setPhilosopherState(State state){
        this.state = state;
        System.out.println(System.currentTimeMillis() +":"+ state +", "+ name+";");
    }
    */
	
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
	
	
}
