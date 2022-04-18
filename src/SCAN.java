import java.util.ArrayList;
import java.util.Collections;

public class SCAN implements Scheduler{
    //Save sequence
    private ArrayList<Integer> sequence;

    //Total head movement
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

        SCAN scan = new SCAN(test);
        scan.SCAN_Start();
    }

    public SCAN(ArrayList<Integer> user_requests){
        reqs = user_requests;
    }

    public void SCAN_Start(){
        CustomScanQueue customScanQueue = new CustomScanQueue(StaticData.INITIAL_HEAD_POINTER, reqs);
        this.totalHeadMv = SCAN_Scheduling(customScanQueue);
        System.out.println("Total head movement = "+this.totalHeadMv+" Cylinders");
    }

    public int SCAN_Scheduling(CustomScanQueue customScanQueue) {
        int totalHeadMv = 0;
        ScanQueueNode startPointer = customScanQueue.startHeadPointer;
        ScanQueueNode cursor = startPointer;

        this.sequence = new ArrayList<>();

        if(startPointer == customScanQueue.tail){
            if(startPointer.req_value == StaticData.CYLINDER_RANGE-1){
                totalHeadMv +=( (StaticData.CYLINDER_RANGE - 1) - (customScanQueue.originalSeq.get(0)) );
            }
        }else {
            if (startPointer.prev == null) {
                totalHeadMv += ((customScanQueue.tail.req_value) - (startPointer.req_value));
            } else {
                totalHeadMv += ((StaticData.CYLINDER_RANGE - 1) - (startPointer.req_value));
                totalHeadMv += ((StaticData.CYLINDER_RANGE - 1) - (customScanQueue.head.req_value));
            }
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
                System.out.print(StaticData.CYLINDER_RANGE-1);
            }

            cursor = startPointer.prev;
            while (cursor!=null){
                sequence.add(cursor.req_value);
                if(cursor.prev != null) {
                    System.out.print(cursor.req_value + " -> ");
                }else{
                    System.out.print(cursor.req_value );

                }
                cursor.visited = true;
                cursor = cursor.prev;
            }
        }
        System.out.println();
        return totalHeadMv;
    }
}

// Custom Double Linked List simulation for simpler queueing operations
class CustomScanQueue {
    ScanQueueNode head;
    ScanQueueNode tail;
    ScanQueueNode startHeadPointer;
    ArrayList<Integer> originalSeq;

    public CustomScanQueue(int head_val, ArrayList<Integer> req_sequence) {
        head = null;
        tail = null;
        startHeadPointer = null;

        req_sequence.add(head_val);
        Collections.sort(req_sequence);
        originalSeq = req_sequence;
        int req_size = req_sequence.size();

        ScanQueueNode prevTempCursor = null;

        for (int i = 0; i < req_size; i++) {
            int req_val = req_sequence.get(i);
            if (i == 0) {
                head = new ScanQueueNode(req_val);
                head.prev = null;

                if(req_val == head_val){
                    startHeadPointer = head;
                }

                prevTempCursor = head;
            } else if (i == req_size - 1) {
                tail = new ScanQueueNode(req_val);
                tail.prev = prevTempCursor;
                prevTempCursor.next = tail;
                tail.next = null;

                if(req_val == head_val){
                    startHeadPointer = tail;
                }

                prevTempCursor = tail;
            } else if (req_val == head_val) {
                startHeadPointer = new ScanQueueNode(req_val);
                prevTempCursor.next = startHeadPointer;
                startHeadPointer.prev = prevTempCursor;

                prevTempCursor = startHeadPointer;
            } else {
                ScanQueueNode current = new ScanQueueNode(req_val);
                current.prev = prevTempCursor;
                prevTempCursor.next = current;

                prevTempCursor = current;
            }
        }
    }

    public void printQueue() {
        ScanQueueNode temp = head;
        while (temp != null) {
            System.out.println(temp.req_value);
            temp = temp.next;
        }
    }
}

class ScanQueueNode {
    int req_value;
    ScanQueueNode next;
    ScanQueueNode prev;
    boolean visited;

    public ScanQueueNode(int req_value) {
        this.req_value = req_value;
        visited = false;
        next = null;
        prev = null;
    }
}