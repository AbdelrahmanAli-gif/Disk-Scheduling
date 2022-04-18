import java.util.ArrayList;

public interface Scheduler {
    ArrayList<Integer> getResultingSequence();
    int getTotalHeadMovements();
    void start();
}
