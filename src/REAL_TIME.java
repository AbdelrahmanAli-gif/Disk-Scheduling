import java.util.ArrayList;
import java.util.Collections;

public class REAL_TIME {
    //Save sequence
    private ArrayList<Integer> sequence;

    //Total head movement
    int totalHeadMv;

    // initial user Requests
    ArrayList<Integer> reqs = new ArrayList<>();


    public void REAL_TIME_Disk_Scheduling(){
        Collections.sort(reqs);
        for(int i=0; i<reqs.size(); i++){
            int req_val = reqs.get(i);
            System.out.print(req_val+" -> ");
            sequence.add(req_val);
        }
        totalHeadMv = reqs.get(reqs.size() - 1);
    }

    public void REAL_TIME_Start(){
        sequence = new ArrayList<>();
        REAL_TIME_Disk_Scheduling();
    }

    public static void main(String[] args) {
        new REAL_TIME().REAL_TIME_Start();
    }
}
