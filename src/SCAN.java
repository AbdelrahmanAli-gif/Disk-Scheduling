import java.util.ArrayList;
import java.util.Collections;

public class SCAN {
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
        int totalHeadMovement = new SCAN().SCAN_Scheduling(customScanQueue);
        System.out.println("Total head movement = "+totalHeadMovement+" Cylinders");
    }

    public int SCAN_Scheduling(CustomScanQueue customScanQueue) {
        int totalHeadMv = 0;
        Node startPointer = customScanQueue.startHeadPointer;
        Node cursor = startPointer;

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
            sequence.add(CYLINDER_RANGE);
            System.out.print(CYLINDER_RANGE);
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
    Node head;
    Node tail;
    Node startHeadPointer;

    public CustomScanQueue(int head_val, ArrayList<Integer> req_sequence) {
        head = null;
        tail = null;
        startHeadPointer = null;

        req_sequence.add(head_val);
        Collections.sort(req_sequence);
        int req_size = req_sequence.size();

        Node prevTempCursor = null;

        for (int i = 0; i < req_size; i++) {
            int req_val = req_sequence.get(i);
            if (i == 0) {
                head = new Node(req_val);
                head.prev = null;

                prevTempCursor = head;
            } else if (i == req_size - 1) {
                tail = new Node(req_val);
                tail.prev = prevTempCursor;
                prevTempCursor.next = tail;
                tail.next = null;

                prevTempCursor = tail;
            } else if (req_val == head_val) {
                startHeadPointer = new Node(req_val);
                prevTempCursor.next = startHeadPointer;
                startHeadPointer.prev = prevTempCursor;

                prevTempCursor = startHeadPointer;
            } else {
                Node current = new Node(req_val);
                current.prev = prevTempCursor;
                prevTempCursor.next = current;

                prevTempCursor = current;
            }
        }
    }

    public void printQueue() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.req_value);
            temp = temp.next;
        }
    }
}

class Node {
    int req_value;
    Node next;
    Node prev;
    boolean visited;

    public Node(int req_value) {
        this.req_value = req_value;
        visited = false;
        next = null;
        prev = null;
    }
}