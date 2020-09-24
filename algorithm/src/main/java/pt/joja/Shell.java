package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Shell {

    // h -> 1,4,13,40...
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
        // sortWithDifferentH(eles);
        System.out.println(Arrays.toString(eles));
    }

    public static void sort(int[] nums) {
        int h = 1;
        while (h < nums.length) {
            h = h * 3 + 1;
        }

        int compCtr = 0;
        int swapCtr = 0;

        while (h > 0) {
            for (int i = 0; i < h; i++) {
                for (int j = i + h; j < nums.length; j += h) {
                    for (int k = j; k >= h; k -= h) {
                        compCtr++;
                        if (nums[k - h] > nums[k]) {
                            int temp = nums[k - h];
                            nums[k - h] = nums[k];
                            nums[k] = temp;
                            swapCtr++;
                        } else {
                            break;
                        }
                    }
                }
            }
            h = h / 3;
        }

        // From《算法4》：
        // 交换次数和比较次数和↑一样的

        // while (h > 0) {
        // for (int i = h; i < nums.length; i++) {
        // for (int j = i; j >= h; j -= h) {
        // compCtr++;
        // if (nums[j - h] > nums[j]) {
        // int temp = nums[j - h];
        // nums[j - h] = nums[j];
        // nums[j] = temp;
        // swapCtr++;
        // } else {
        // break;
        // }
        // }
        // }
        // h = h / 3;
        // }

        System.out.println("[Shell.sort] compared: " + compCtr);
        System.out.println("[Shell.sort] swaped  : " + swapCtr);
    }

    public static void sortWithDifferentH(int[] nums) {
        int h = nums.length / 2;

        int compCtr = 0;
        int swapCtr = 0;

        while (h > 0) {
            for (int i = 0; i < h; i++) {
                for (int j = i + h; j < nums.length; j += h) {
                    for (int k = j; k >= h; k -= h) {
                        compCtr++;
                        if (nums[k - h] > nums[k]) {
                            int temp = nums[k - h];
                            nums[k - h] = nums[k];
                            nums[k] = temp;
                            swapCtr++;
                        } else {
                            break;
                        }
                    }
                }
            }
            h = h / 2;
        }

        System.out.println("[Shell.sort] compared: " + compCtr);
        System.out.println("[Shell.sort] swaped  : " + swapCtr);
    }
}
