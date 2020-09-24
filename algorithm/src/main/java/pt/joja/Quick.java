package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Quick {

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

    static int compCtr = 0;
    static int swapCtr = 0;

    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
        System.out.println("[Quick.sort] compared: " + compCtr);
        System.out.println("[Quick.sort] swaped  : " + swapCtr);
    }

    public static void sort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }

        int pivot = nums[left];

        int posL = left;
        int posR = right + 1;
        // while (posL < posR) {
        // compCtr++;
        // if (nums[posL] > pivot) {
        // swapCtr++;
        // int temp = nums[posL];
        // nums[posL] = nums[--posR];
        // nums[posR] = temp;
        // } else {
        // posL++;
        // }
        // }
        // posL--;

        // From 算法4：
        // 交换次数更少

        while (true) {
            while (true) {
                compCtr++;
                if (nums[++posL] > pivot) {
                    break;
                }
                if (posL == right) {
                    break;
                }
            }
            while (true) {
                compCtr++;
                if (nums[--posR] <= pivot) {
                    break;
                }
                if (posR == left) {
                    break;
                }
            }
            if (posL >= posR) {
                break;
            }
            swapCtr++;
            int temp = nums[posL];
            nums[posL] = nums[posR];
            nums[posR] = temp;
        }

        swapCtr++;
        nums[left] = nums[posR];
        nums[posR] = pivot;
        sort(nums, left, posR - 1);
        sort(nums, posR + 1, right);
    }

}
