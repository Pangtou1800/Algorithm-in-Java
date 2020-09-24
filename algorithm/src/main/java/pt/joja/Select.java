package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Select {

    public static void main(String[] args) {
        int nums = 100;

        Random rand = new Random(47);
        int[] eles = new int[nums];
        int[] eles2 = new int[nums];
        for (int i = 0; i < nums; i++) {
            eles[i] = rand.nextInt(1000);
        }
        System.arraycopy(eles, 0, eles2, 0, nums);

        System.out.println(Arrays.toString(eles));
        sort(eles);
        System.out.println(Arrays.toString(eles));
    }

    public static void sort(int[] eles) {
        int compCtr = 0;
        int swapCtr = 0;
        for (int i = 0; i < eles.length; i++) {
            int minPos = i;
            for (int j = i + 1; j < eles.length; j++) {
                compCtr++;
                if (eles[minPos] > eles[j]) {
                    minPos = j;
                }
            }
            swapCtr++;
            int temp = eles[i];
            eles[i] = eles[minPos];
            eles[minPos] = temp;
        }
        System.out.println("[Select.sort] compared: " + compCtr);
        System.out.println("[Select.sort] swaped  : " + swapCtr);
    }
}
