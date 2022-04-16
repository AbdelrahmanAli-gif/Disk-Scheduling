import java.util.ArrayList;

public class C_SCAN {
    //Save sequence
    private ArrayList<Integer> sequence;

    //Total Head Movement
    int totalHeadMv;

    // initial user Requests
    ArrayList<Integer> reqs;

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(98);
        test.add(183);
        test.add(37);
        test.add(122);
        test.add(14);
        test.add(124);
        test.add(65);
        test.add(67);

        new C_SCAN(test).C_SCAN_Start();
    }

    public C_SCAN(ArrayList<Integer> user_requests){
        reqs = user_requests;
    }

    public void C_SCAN_Start(){
        CustomScanQueue customScanQueue = new CustomScanQueue(StaticData.INITIAL_HEAD_POINTER, reqs);
        this.totalHeadMv = CSCAN_Scheduling(customScanQueue);
        System.out.println("Total head movement = "+this.totalHeadMv+" Cylinders");
    }

    public int CSCAN_Scheduling(CustomScanQueue customScanQueue) {
        int totalHeadMv = 0;
        ScanQueueNode startPointer = customScanQueue.startHeadPointer;
        ScanQueueNode cursor = startPointer;

        this.sequence = new ArrayList<>();

        if(startPointer.prev==null){
            totalHeadMv += ( (customScanQueue.tail.req_value) - (startPointer.req_value) );
        }else{
            totalHeadMv += ( (StaticData.CYLINDER_RANGE-1) - (startPointer.req_value) );
            totalHeadMv += ( StaticData.CYLINDER_RANGE );
            totalHeadMv += startPointer.prev.req_value;
        }

        while (cursor != null) {
            sequence.add(cursor.req_value);
            System.out.print(cursor.req_value+" -> ");
            cursor.visited = true;
            cursor = cursor.next;
        }

        if(startPointer.prev != null){
            if(startPointer.req_value != StaticData.CYLINDER_RANGE-1){

                sequence.add(StaticData.CYLINDER_RANGE-1);
                System.out.print((StaticData.CYLINDER_RANGE-1)+" -> ");
            }
            System.out.print(0);
            cursor = customScanQueue.head;
            while (cursor!=null && (cursor.visited!=true) ){
                sequence.add(cursor.req_value);
                System.out.print(" -> "+ cursor.req_value);
                cursor.visited = true;
                cursor = cursor.next;
            }
        }
        System.out.println();
        return totalHeadMv;
    }
}
