import java.util.ArrayList;
import java.util.Collections;

public class REAL_TIME implements Scheduler{
    //Save sequence
    private ArrayList<Integer> sequence;

    //Total head movement
    int totalHeadMv;

    // initial user Requests
    ArrayList<Integer> reqs = new ArrayList<>();


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


    public void REAL_TIME_Start() {

        sequence = new ArrayList<>();
        reqs.add(38);
        reqs.add(180);
        reqs.add(130);
        reqs.add(10);
        reqs.add(50);
        reqs.add(15);
        reqs.add(190);
        reqs.add(90);
        reqs.add(150);

        REAL_TIME_Disk_Scheduling();
    }

    public static void main(String[] args) {
        new REAL_TIME().REAL_TIME_Start();
    }
}
