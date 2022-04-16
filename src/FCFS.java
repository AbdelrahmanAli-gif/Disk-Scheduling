import java.util.ArrayList;

public class FCFS {
    ArrayList<Integer> sequence;
    private int headMovements;
    private int currentHead;
    public FCFS(){
        sequence = new ArrayList<>();
        headMovements = 0;
        currentHead = StaticData.INITIAL_HEAD_POINTER;
    }
    public void FCFS(int[] requests) {
        for(Integer request: requests){
            sequence.add(currentHead);
            headMovements += Math.abs(request - currentHead);
            currentHead = request;
        }
        System.out.println(sequence);
        System.out.println(headMovements);
    }

    public static void main(String[] args) {
        int[] requests = {98, 183, 37, 122, 14, 124, 65, 67};
        StaticData.INITIAL_HEAD_POINTER = 53;
        FCFS fcfs = new FCFS();
        fcfs.FCFS(requests);
    }
}
