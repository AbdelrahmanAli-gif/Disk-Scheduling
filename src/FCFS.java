import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FCFS {
    List<Integer> sequence;
    private int headMovements;
    private int currentHead;
    ArrayList<Integer> requests;
    public FCFS(ArrayList<Integer> reqs){
        sequence = new ArrayList<>();
        headMovements = 0;
        currentHead = StaticData.INITIAL_HEAD_POINTER;
        requests = reqs;
    }
    public void FCFS() {
        for(Integer request: requests){
            sequence.add(currentHead);
            headMovements += Math.abs(request - currentHead);
            currentHead = request;
        }
        System.out.println(sequence);
        System.out.println(headMovements);
    }

    public static void main(String[] args) {
        ArrayList<Integer> reqs = new ArrayList<Integer>();
        reqs.add(98);
        reqs.add(183);
        reqs.add(37);
        reqs.add(122);
        reqs.add(14);
        reqs.add(124);
        reqs.add(65);
        reqs.add(67);

        StaticData.INITIAL_HEAD_POINTER = 53;
        FCFS fcfs = new FCFS(reqs);
        fcfs.FCFS();
    }
}
