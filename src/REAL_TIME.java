import java.util.ArrayList;
import java.util.Collections;

public class REAL_TIME implements Scheduler{
    //Save sequence
    private ArrayList<Integer> sequence;

    //Total head movement
    int totalHeadMv;

    // initial user Requests
    ArrayList<Integer> reqs = new ArrayList<>();

    @Override
    public ArrayList<Integer> getResultingSequence() {
        return sequence;
    }

    @Override
    public int getTotalHeadMovements() {
        return totalHeadMv;
    }

    @Override
    public void start() {
        REAL_TIME_Start();
    }

    public void REAL_TIME_Disk_Scheduling() {
        Collections.sort(reqs);
        int headPos = StaticData.INITIAL_HEAD_POINTER = 0;
        sequence.add(headPos);

        for (int i = 0; i < reqs.size(); i++) {
            totalHeadMv += Math.abs(headPos - reqs.get(i));
            headPos = reqs.get(i);
            sequence.add(headPos);
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (i == sequence.size() - 1) {
                System.out.print(sequence.get(i));
            } else {
                System.out.print(sequence.get(i) + " -> ");
            }
        }
        System.out.println();

        System.out.println("Total Head Movement is: " + totalHeadMv);
    }

    public REAL_TIME(ArrayList<Integer> reqs){
        this.reqs = reqs;
    }


    public void REAL_TIME_Start() {
        sequence = new ArrayList<>();
        REAL_TIME_Disk_Scheduling();
    }
}
