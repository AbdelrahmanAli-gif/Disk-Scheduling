import java.util.ArrayList;
import java.util.Collections;

public class C_Look implements Scheduler {
    private ArrayList<Integer> queue;
    private ArrayList<Integer> sequence;
    private int totalMovement;
    private int initialHeadPos;

    public C_Look(ArrayList<Integer> requests) {
        initialHeadPos = StaticData.INITIAL_HEAD_POINTER;
        queue = new ArrayList<>(requests);
        sequence = new ArrayList<>();
        totalMovement = 0;
    }


    public void cLookAlgo() {
        sequence = new ArrayList<>();
        sequence.add(initialHeadPos);
        ArrayList<Integer> copy = new ArrayList<>(queue);
        copy.sort(Integer::compareTo);
        boolean started = false;
        int start = -1;
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i) >= StaticData.INITIAL_HEAD_POINTER && started == false) {
                totalMovement += Math.abs(StaticData.INITIAL_HEAD_POINTER - copy.get(i));
                started = true;
                start = i;
                StaticData.INITIAL_HEAD_POINTER = copy.get(i);
                sequence.add(StaticData.INITIAL_HEAD_POINTER);
                continue;
            }
            if (copy.get(i) >= StaticData.INITIAL_HEAD_POINTER) {
                totalMovement += Math.abs(StaticData.INITIAL_HEAD_POINTER - copy.get(i));
                StaticData.INITIAL_HEAD_POINTER = copy.get(i);
                sequence.add(StaticData.INITIAL_HEAD_POINTER);
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
            totalMovement += Math.abs(StaticData.INITIAL_HEAD_POINTER - copy.get(i));
            StaticData.INITIAL_HEAD_POINTER = copy.get(i);
            sequence.add(StaticData.INITIAL_HEAD_POINTER);
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
        System.out.println("Total head Movement is " + totalMovement + " Cylinders");

    }


    @Override
    public ArrayList<Integer> getResultingSequence() {
        return sequence;
    }

    @Override
    public int getTotalHeadMovements() {
        return totalMovement;
    }

    @Override
    public void start() {
        cLookAlgo();
    }

    public static void main(String[] args) {
        int[] Processes = {98, 183, 37, 122, 14, 124, 65, 67};
        ArrayList<Integer> queue = new ArrayList<>();
        for (int process : Processes) {
            queue.add(process);
        }
        C_Look l = new C_Look( queue);

        l.cLookAlgo();
    }
}
