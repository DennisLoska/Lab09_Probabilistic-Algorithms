import java.util.Random;

public class Philosopher implements Runnable {

    private final Fork leftFork;
    private final Fork rightFork;
    private int ID;
    private Random rand;
    private Statistics stats;
    private long startTime;
    private long endTime;
    private long duration;


    Philosopher(int ID, Fork leftFork, Fork rightFork) {
        this.ID = ID;
        rand = new Random();

        this.leftFork = leftFork;
        this.rightFork = rightFork;

        stats = new Statistics();
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        while (true) {

            think();
            synchronized (this) {
                take_leftFork();
                synchronized (this) {
                    take_rightFork();
                    eat();
                }
            }
            drop_leftFork();
            drop_rightFork();

            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println(duration);

            if (duration > 5000) {
                stats.printEatStats();
                System.out.println(Thread.currentThread().getName() + " : is going to shutdown!");
                Thread.currentThread().stop();
            }
        }
    }

    private void drop_leftFork() {
        leftFork.drop();
        System.out.println(getFullInfo() + " dropped left fork #" + leftFork.getForkID());
        halt();
    }

    private void drop_rightFork() {
        rightFork.drop();
        System.out.println(getFullInfo() + " dropped right fork #" + rightFork.getForkID());
        halt();
    }

    private void take_rightFork() {
        rightFork.take();
        System.out.println(getFullInfo() + " took right fork #" + rightFork.getForkID());
        halt();
    }

    private void take_leftFork() {
        leftFork.take();
        System.out.println(getFullInfo() + " took left fork #" + leftFork.getForkID());
        halt();

    }

    private void eat() {
        System.out.println(getFullInfo() + " is eating");
        stats.increaseEatCounter(this.ID);
        halt();
        System.out.println(getFullInfo() + " has stopped eating");
    }

    private void think() {
        System.out.println(getFullInfo() + " is thinking");
        halt();
        System.out.println(getFullInfo() + " has stopped thinking and wants to eat.");
    }

    private void halt() {
        try {
//            Thread.sleep(100);
            Thread.sleep(rand.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getFullInfo() {
        String result = "";
        result += "Philosopher #" + this.ID + " running on  " + Thread.currentThread().getName() + " ";
        return result;
    }

    int getID() {
        return this.ID;

    }

    public long getDuration() {
        return duration;
    }
}
