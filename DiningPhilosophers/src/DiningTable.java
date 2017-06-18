
public class DiningTable{

    private static Philosopher[] philosophers = new Philosopher[5];
    private static Statistics stats;
    private Fork[] forks = new Fork[5];
    private Thread[] threads = new Thread[5];

    private Fork leftFork;
    private Fork rightFork;

    private DiningTable() {
        stats = new Statistics();
    }

    public static void main(String[] args) {
        DiningTable table = new DiningTable();
        table.init();
    }

    private void init() {

        for (int i = 0; i < forks.length; i++){
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < philosophers.length; i++) {
            leftFork = forks[i];
            rightFork = forks[(i + 1) % 5];

            philosophers[i] = new Philosopher(i, leftFork, rightFork);

            threads[i] = new Thread (philosophers[i]);
            threads[i].start();

        }
    }

    static Philosopher getPhilosopher(int i){
        return philosophers[i];
    }

}
