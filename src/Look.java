import java.util.ArrayList;
import java.util.Collections;

public class Look implements Scheduler{
    private ArrayList<Integer> queue;
    private ArrayList<Integer> sequence;

    public Look(int initialHeadPos) {
        StaticData.INITIAL_HEAD_POINTER = initialHeadPos;
        queue = new ArrayList<>();
        sequence = new ArrayList<>();
    }

    public void lookAlgo(ArrayList<Integer> queue, int initialHeadPos) {
        int movement = 0;
        sequence = new ArrayList<>();
        ArrayList<Integer> copy = new ArrayList<>(queue);
        copy.sort(Integer::compareTo);
        boolean started = false;
        int start = -1;
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) >= initialHeadPos && started == false) {
                movement += Math.abs(initialHeadPos - copy.get(i));
                started = true;
                start = i;
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
                continue;
            }
            if (copy.get(i) >= initialHeadPos) {
                movement += Math.abs(initialHeadPos - copy.get(i));
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
            }
        }
        if (start >= 0) {
            for (int i = start; i < copy.size(); i++) {
                copy.set(i, 0);
            }
        }
        copy.sort(Collections.reverseOrder());
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) == 0) {
                break;
            }
            movement += Math.abs(initialHeadPos - copy.get(i));
            initialHeadPos = copy.get(i);
            sequence.add(initialHeadPos);
        }
        System.out.println("********* Sequence is! *********");
        for (int i = 0; i < sequence.size(); i++) {
            if (i == sequence.size() - 1) {
                System.out.print(sequence.get(i));

            } else {
                System.out.print(sequence.get(i) + " -> ");

            }
        }
        System.out.println();
        System.out.println("Total head Movement is " + movement + " Cylinders");
    }

    public void cLookAlgo(ArrayList<Integer> queue, int initialHeadPos) {
        int movement = 0;
        sequence = new ArrayList<>();
        ArrayList<Integer> copy = new ArrayList<>(queue);
        copy.sort(Integer::compareTo);
        boolean started = false;
        int start = -1;
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) >= initialHeadPos && started == false) {
                movement += Math.abs(initialHeadPos - copy.get(i));
                started = true;
                start = i;
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
                continue;
            }
            if (copy.get(i) >= initialHeadPos) {
                movement += Math.abs(initialHeadPos - copy.get(i));
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
            }
        }
        if (start >= 0) {
            for (int i = start; i < copy.size(); i++) {
                copy.set(i, 0);
            }
        }
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) == 0) {
                break;
            }
            movement += Math.abs(initialHeadPos - copy.get(i));
            initialHeadPos = copy.get(i);
            sequence.add(initialHeadPos);
        }
        System.out.println("********* Sequence is! *********");
        for (int i = 0; i < sequence.size(); i++) {
            if (i == sequence.size() - 1) {
                System.out.print(sequence.get(i));

            } else {
                System.out.print(sequence.get(i) + " -> ");
            }
        }
        System.out.println();
        System.out.println("Total head Movement is " + movement + " Cylinders");

    }


    public static void main(String[] args) {
        Look l = new Look(53);
        int[] Processes = {98, 183, 37, 122, 14, 124, 65, 67};
        ArrayList<Integer> queue = new ArrayList<>();
        for (int process : Processes) {
            queue.add(process);
        }
        l.lookAlgo(queue, StaticData.INITIAL_HEAD_POINTER);

        l.cLookAlgo(queue, StaticData.INITIAL_HEAD_POINTER);
    }
}
