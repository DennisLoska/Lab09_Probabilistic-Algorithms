
class Statistics {

    private static int[] eatStats;

    Statistics(){
        eatStats = new int[5];
    }

    void increaseEatCounter(int phID){
        eatStats[phID]++;
    }

    void printEatStats(){
        for (int i = 0; i < eatStats.length; i++){
            System.out.println("Philosopher #" + DiningTable.getPhilosopher(i).getID() + " : times of eating = " + eatStats[i]);
        }
    }

}
