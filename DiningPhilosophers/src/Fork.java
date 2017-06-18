
public class Fork {
    private int forkID;
    private boolean isUsed;

    Fork(int forkID){
        this.forkID = forkID;
    }

    void take(){
        this.isUsed = true;
    }

    void drop(){
        this.isUsed = false;
    }

    public boolean isUsed(){
        return isUsed;
    }

    int getForkID() {
        return forkID;
    }
}
