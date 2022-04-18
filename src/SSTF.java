import java.util.ArrayList;
import java.util.Collections;

public class SSTF implements Scheduler{
    private ArrayList<Integer> sequence;
    private int totalHeadMovement;
    private ArrayList<Integer> requests;
    private int headPointer;

    public SSTF(ArrayList<Integer> requests){
        this.requests = requests;
        this.sequence = new ArrayList<>();
        this.totalHeadMovement = 0;
        this.headPointer = StaticData.INITIAL_HEAD_POINTER;
    }

    public static void main(String[] args) {
        ArrayList<Integer> requests = new ArrayList<>();
        requests.add(98);
        requests.add(183);
        requests.add(37);
        requests.add(122);
        requests.add(14);
        requests.add(124);
        requests.add(65);
        requests.add(67);
        SSTF sstf = new SSTF(requests);
        //sstf.Start_Scheduling();
    }

//    public void Start_Scheduling(){
//
//        SSTF_Scheduling();
//    }

    public void SSTF_Scheduling(){
        Queue queue = new Queue(headPointer, requests);
        Node current = queue.startNode;
        while (current != null){
            sequence.add(current.value);
            if (current.next == null && current.previous == null)
                current = null;
            else if (current.previous == null) {
                totalHeadMovement += current.next.value - current.value;
                current = current.next;
                queue.remove(current.previous);
            }
            else if (current.next == null) {
                totalHeadMovement += current.value - current.previous.value;
                current = current.previous;
                queue.remove(current.next);
            }
            else{
                int previousNode = current.value - current.previous.value;
                int nextNode = current.next.value - current.value;
                if (previousNode < nextNode){
                    totalHeadMovement += previousNode;
                    current = current.previous;
                    queue.remove(current.next);
                }
                else {
                    totalHeadMovement += nextNode;
                    current = current.next;
                    queue.remove(current.previous);
                }
            }
        }
        //printSequence();
    }

//    private void printSequence(){
//        for (int i = 0; i < sequence.size(); i++){
//            System.out.print(sequence.get(i));
//            if (i < sequence.size() -1)
//                System.out.print(" -> ");
//        }
//        System.out.println();
//        System.out.println("Total Head Movement = " + totalHeadMovement + " Cylinders");
//    }

    @Override
    public ArrayList<Integer> getResultingSequence() {
        return sequence;
    }

    @Override
    public int getTotalHeadMovements() {
        return totalHeadMovement;
    }

    @Override
    public void start() {
        SSTF_Scheduling();
    }

    class Node{
        int value;
        Node next;
        Node previous;
        boolean visited;

        public Node(int value){
            this.value = value;
            next = null;
            previous = null;
            visited = false;
        }
    }

    class Queue {
        Node head = null;
        Node tail = null;
        Node startNode;
        public Queue(int headPointer, ArrayList<Integer> requests) {
            //headPointer = head;
            requests.add(headPointer);
            Collections.sort(requests);
            for (int i = 0; i < requests.size(); i++){
                add(requests.get(i), headPointer);
            }
        }

        private void add(int value, int startValue){
            Node newNode = new Node(value);
            if (head == null){
                head = newNode;
                tail = newNode;
                newNode.next = null;
                newNode.previous = null;
            }
            else {
                Node temp = head;
                while (temp.next != null)
                    temp = temp.next;
                temp.next = newNode;
                newNode.previous = temp;
                newNode.next = null;
                tail = newNode;
            }
            if (value == startValue)
                startNode = newNode;
        }

        private void remove(Node node){
            if (node == head){
                head.next.previous = null;
                head = head.next;
            }
            else {
                Node temp = head;
                while (temp.value != node.value)
                    temp = temp.next;
                if (temp == tail) {
                    tail.previous.next = null;
                    tail = tail.previous;
                }
                else {
                    temp.next.previous = temp.previous;
                    temp.previous.next = temp.next;
                    temp.next = null;
                    temp.previous = null;
                }
            }
        }
    }
}
