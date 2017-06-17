
public class DiningTable {

    private Philosopher[] philosophers = new Philosopher[5];
    private Fork[] forks = new Fork[5];

    private Fork leftFork;
    private Fork rightFork;

    private DiningTable() {
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

            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }

}
