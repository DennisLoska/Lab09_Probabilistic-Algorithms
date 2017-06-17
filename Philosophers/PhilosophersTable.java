package philosophersProblem;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PhilosophersTable {
	
	 //fork Array, necessary to determine which philosopher can have which fork
	//1 steht für hat Gabel 
	//beispiel: {11000} der erste philosoph hat Gabeln auf beiden Seiten, der 5te nur auf seiner Rechten Seite
     static int[] forkArray = new int[5];
	static long startingTime = System.currentTimeMillis();

	public static void main(String[] args) {
		 PhilosophersTable philosophersTable = new PhilosophersTable();
		 philosophersTable.start();

	}
	
	public PhilosophersTable(){
		//lege überall eine gabel hin
		for(int i =0;i<forkArray.length;i++){
			forkArray[i]=1;
		}
	}
	/*
	 * lasset die Philosophen denken und speisen!!!
	 */
	public void start(){
		Thread phil1 = new Thread(new Philosopher("Pythagoras",1));
		Thread phil2 = new Thread(new Philosopher("Seneca",2));
		Thread phil3 = new Thread(new Philosopher("Thomas von Aquin",3));
		Thread phil4 = new Thread(new Philosopher("Platon",4));
		Thread phil5 = new Thread(new Philosopher("Erasmus aus Rotterdamm",5));
		
		phil1.start();
		phil2.start();
		phil3.start();
		phil4.start();
		phil5.start();
		
		/*class DisplayState extends TimerTask {
		    public void run() {
		    	System.out.println(phil1.getState());
				System.out.println(phil2.getState());
				System.out.println(phil3.getState());
				System.out.println(phil4.getState());
				System.out.println(phil5.getState());
				System.out.println("");
		    }
		}	

		Timer timer = new Timer();
		timer.schedule(new DisplayState(), 0, 2000);
		*/
		
		
		/*while(true){
			for(int i=0;i<5;i++){
				phil1.setPriority((5+i)%5+1);
				phil1.setPriority((4+i)%5+1);
				phil1.setPriority((3+i)%5+1);
				phil1.setPriority((2+i)%5+1);
				phil1.setPriority((1+i)%5+1);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n");
			}
		}*/
		
	}
	
	public static synchronized int[] getForkArray(){
		return forkArray;
	}
	public static synchronized void setForkArray(int index, int value){
		forkArray[index] = value;
	}
	
	
	//zwei alghorythmen um den deadlock zu vermeiden: 
	// 1. random entscheiden welcher Thread durckommt un welcher yield
	// oder nummerieren

}
