
class Statistics {

    private int[] eatStats;

    Statistics(){
        eatStats = new int[5];
    }

    void increaseEatCounter(Philosopher ph){
        eatStats[ph.getID()]++;

    }

}
