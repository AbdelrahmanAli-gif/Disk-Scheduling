import java.util.ArrayList;

public class C_SCAN {
    // 0-> 199 range of movement
    private final int CYLINDER_RANGE = 200;

    //Save sequence
    private ArrayList<Integer> sequence;

    public static void main(String[] args) {
        ArrayList<Integer> reqs = new ArrayList<>();
        reqs.add(98);
        reqs.add(183);
        reqs.add(37);
        reqs.add(122);
        reqs.add(14);
        reqs.add(124);
        reqs.add(65);
        reqs.add(67);

        CustomScanQueue customScanQueue = new CustomScanQueue(53, reqs);
        int totalHeadMovement = new C_SCAN().CSCAN_Scheduling(customScanQueue);
        System.out.println("Total head movement = "+totalHeadMovement+" Cylinders");
    }

    public int CSCAN_Scheduling(CustomScanQueue customScanQueue) {
        int totalHeadMv = 0;
        ScanQueueNode startPointer = customScanQueue.startHeadPointer;
        ScanQueueNode cursor = startPointer;

        this.sequence = new ArrayList<>();

        if(startPointer.prev==null){
            totalHeadMv += ( (customScanQueue.tail.req_value) - (startPointer.req_value) );
        }else{
            totalHeadMv += ( (CYLINDER_RANGE-1) - (startPointer.req_value) );
            totalHeadMv += ( CYLINDER_RANGE );
            totalHeadMv += startPointer.prev.req_value;
        }

        while (cursor != null) {
            sequence.add(cursor.req_value);
            System.out.print(cursor.req_value+" -> ");
            cursor.visited = true;
            cursor = cursor.next;
        }

        if(startPointer.prev != null){
            sequence.add(CYLINDER_RANGE-1);
            System.out.print((CYLINDER_RANGE-1)+" -> ");
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
