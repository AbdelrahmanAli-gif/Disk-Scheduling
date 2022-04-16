import java.util.ArrayList;
import java.util.Collections;

public class Look {
    private ArrayList<Integer> queue;
    private int initialHeadPos;
    private ArrayList<Integer> sequence;

    public Look(int initialHeadPos) {
        this.initialHeadPos = initialHeadPos;
        queue = new ArrayList<>();
        sequence = new ArrayList<>();
    }

    public void lookAlgo(ArrayList<Integer> queue, int initialHeadPos) {
        sequence = new ArrayList<>();
        ArrayList<Integer> copy = (ArrayList<Integer>) queue.clone();
        copy.sort(Integer::compareTo);
        boolean started = false;
        int start = 0;
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) >= initialHeadPos && started == false) {
                started = true;
                start = i;
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
                continue;
            }
            if (copy.get(i) >= initialHeadPos) {
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
            }
        }
        for (int i = start; i < copy.size(); i++) {
            copy.set(i, 0);
        }
        copy.sort(Collections.reverseOrder());
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) == 0) {
                break;
            }
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
    }

    public void cLookAlgo(ArrayList<Integer> queue, int initialHeadPos) {
        sequence = new ArrayList<>();
        ArrayList<Integer> copy = (ArrayList<Integer>) queue.clone();
        copy.sort(Integer::compareTo);
        boolean started = false;
        int start = 0;
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) >= initialHeadPos && started == false) {
                started = true;
                start = i;
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
                continue;
            }
            if (copy.get(i) >= initialHeadPos) {
                initialHeadPos = copy.get(i);
                sequence.add(initialHeadPos);
            }
        }
        for (int i = start; i < copy.size(); i++) {
            copy.set(i, 0);
        }
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) == 0) {
                break;
            }
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
    }


    public static void main(String[] args) {
        Look l = new Look(53);
        int[] Processes = {98, 183, 37, 122, 14, 124, 65, 67};
        ArrayList<Integer> queue = new ArrayList<>();
        for (int process : Processes) {
            queue.add(process);
        }
        l.lookAlgo(queue, l.initialHeadPos);

        l.cLookAlgo(queue, l.initialHeadPos);
    }
}
