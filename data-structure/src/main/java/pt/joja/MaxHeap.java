package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class MaxHeap {

    public static void main(String[] args) {
        int nums = 100;

        Random rand = new Random(47);
        int[] eles = new int[nums];
        for (int i = 0; i < nums; i++) {
            eles[i] = rand.nextInt(1000);
        }

        System.out.println(Arrays.toString(eles));

        MaxHeap maxHeap = new MaxHeap(eles.length);
        for (int ele : eles) {
            maxHeap.add(ele);
        }
        for (int i = eles.length - 1; i >= 0; i--) {
            eles[i] = maxHeap.remove();
        }
        System.out.println("[Heap.sort] compared: " + compCtr);
        System.out.println("[Heap.sort] swaped  : " + swapCtr);
        System.out.println(Arrays.toString(eles));

    }

    private static int compCtr = 0;
    private static int swapCtr = 0;

    private int[] eles;

    private int size = 0;

    public MaxHeap(int maxSize) {
        eles = new int[maxSize + 1];
    }

    public void add(int ele) {
        if (size < eles.length) {
            eles[++size] = ele;
            swim(size);
            return;
        }
        throw new RuntimeException("Heap is full.");
    }

    public int remove() {
        if (size > 0) {
            int result = eles[1];
            eles[1] = eles[size--];
            sink(1);
            return result;
        }
        throw new RuntimeException("Heap is empty.");
    }

    public void sink(int index) {
        while (2 * index <= size) {
            int child = 2 * index;
            if (child < size) {
                compCtr++;
                if (eles[child] < eles[child + 1]) {
                    child++;
                }
            }
            compCtr++;
            if (eles[index] > eles[child]) {
                break;
            }
            swapCtr++;
            int temp = eles[index];
            eles[index] = eles[child];
            eles[child] = temp;
            index = child;
        }
    }

    public void swim(int index) {
        while (index > 0) {
            int father = index / 2;
            compCtr++;
            if (eles[index] < eles[father]) {
                break;
            }
            swapCtr++;
            int temp = eles[index];
            eles[index] = eles[father];
            eles[father] = temp;
            index = father;
        }
    }

    // 1
    // 2 3
    // 4 5 6 7

}
