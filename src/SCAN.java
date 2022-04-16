import java.util.ArrayList;
import java.util.Collections;

public class SCAN {
    // 0-> 199 range of movement
    private int CYLINDER_RANGE = 200;

    //Save sequence
    private ArrayList<Integer> sequence;

    //Total head movement
    int totalHeadMv;

    //Initial Head Pointer Value
    int initialHeadPointer;

    // initial user Requests
    ArrayList<Integer> reqs = new ArrayList<>();

    public static void main(String[] args) {
        new SCAN().SCAN_Start();
    }

    public void SCAN_Start(){
        reqs.add(98);
        reqs.add(183);
        reqs.add(37);
        reqs.add(122);
        reqs.add(14);
        reqs.add(124);
        reqs.add(65);
        reqs.add(67);

        initialHeadPointer = 53;

        CustomScanQueue customScanQueue = new CustomScanQueue(initialHeadPointer, reqs);
        this.totalHeadMv = new SCAN().SCAN_Scheduling(customScanQueue);
        System.out.println("Total head movement = "+this.totalHeadMv+" Cylinders");
    }

    public int SCAN_Scheduling(CustomScanQueue customScanQueue) {
        int totalHeadMv = 0;
        ScanQueueNode startPointer = customScanQueue.startHeadPointer;
        ScanQueueNode cursor = startPointer;

        this.sequence = new ArrayList<>();

        if(startPointer.prev==null){
            totalHeadMv += ( (customScanQueue.tail.req_value) - (startPointer.req_value) );
        }else{
            totalHeadMv += ( (CYLINDER_RANGE-1) - (startPointer.req_value) );
            totalHeadMv += ( (CYLINDER_RANGE-1) - (customScanQueue.head.req_value) );
        }

        while (cursor != null) {
            sequence.add(cursor.req_value);
            System.out.print(cursor.req_value+" -> ");
            cursor.visited = true;
            cursor = cursor.next;
        }

        if(startPointer.prev != null){
            sequence.add(CYLINDER_RANGE-1);
            System.out.print(CYLINDER_RANGE-1);
            cursor = startPointer.prev;
            while (cursor!=null){
                sequence.add(cursor.req_value);
                System.out.print(" -> "+ cursor.req_value);
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

    public CustomScanQueue(int head_val, ArrayList<Integer> req_sequence) {
        head = null;
        tail = null;
        startHeadPointer = null;

        req_sequence.add(head_val);
        Collections.sort(req_sequence);
        int req_size = req_sequence.size();

        ScanQueueNode prevTempCursor = null;

        for (int i = 0; i < req_size; i++) {
            int req_val = req_sequence.get(i);
            if (i == 0) {
                head = new ScanQueueNode(req_val);
                head.prev = null;

                prevTempCursor = head;
            } else if (i == req_size - 1) {
                tail = new ScanQueueNode(req_val);
                tail.prev = prevTempCursor;
                prevTempCursor.next = tail;
                tail.next = null;

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