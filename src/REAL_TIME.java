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
            if(i != reqs.size() - 1) {
                System.out.print(req_val + " -> ");
            }else{
                System.out.println(req_val);
            }
            sequence.add(req_val);
        }
        totalHeadMv = reqs.get(reqs.size() - 1);
        System.out.println("Total Head Movement = "+totalHeadMv);
    }

    public void REAL_TIME_Start(){
        sequence = new ArrayList<>();
        reqs.add(98);
        reqs.add(183);
        reqs.add(37);
        reqs.add(122);
        reqs.add(14);
        reqs.add(124);
        reqs.add(65);
        reqs.add(67);

        REAL_TIME_Disk_Scheduling();
    }

    public static void main(String[] args) {
        new REAL_TIME().REAL_TIME_Start();
    }
}
