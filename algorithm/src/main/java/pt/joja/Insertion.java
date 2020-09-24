package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Insertion {

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

    public static void sort(int[] nums) {
        int compCtr = 0;
        int swapCtr = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                compCtr++;
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    swapCtr++;
                } else {
                    break;
                }
            }
        }
        System.out.println("[Insertion.sort] compared: " + compCtr);
        System.out.println("[Insertion.sort] swaped  : " + swapCtr);
    }

}
