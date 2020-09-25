package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Merge {

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
        System.out.println("-------------------");
        compCtr = 0;
        copyCtr = 0;
        System.out.println(Arrays.toString(eles2));
        sortBU(eles2);
        System.out.println(Arrays.toString(eles2));
    }

    public static void sortBU(int[] nums) {
        int len = nums.length;
        for (int sz = 1; sz < len; sz = sz + sz) {
            for (int lo = 0; lo < len - sz; lo += (sz + sz)) {
                merge(nums, lo, lo + sz, Math.min(len, lo + sz + sz));
            }
        }
        System.out.println("[Merge.sort] compared: " + compCtr);
        System.out.println("[Merge.sort] copied  : " + copyCtr);
    }

    public static void sort(int[] nums) {
        sort(nums, 0, nums.length);
        System.out.println("[Merge.sort] compared: " + compCtr);
        System.out.println("[Merge.sort] copied  : " + copyCtr);
    }

    public static void sort(int[] nums, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int mid = (right + left) / 2;
        sort(nums, left, mid);
        sort(nums, mid, right);
        merge(nums, left, mid, right);
    }

    static int compCtr = 0;
    static int copyCtr = 0;

    public static void merge(int[] nums, int left, int mid, int right) {
        int[] result = new int[nums.length];

        int idx = left;
        int i = left, j = mid;
        while (i < mid && j < right) {
            compCtr++;
            if (nums[i] < nums[j]) {
                result[idx++] = nums[i++];
            } else {
                result[idx++] = nums[j++];
            }
            copyCtr++;
        }
        while (i < mid) {
            result[idx++] = nums[i++];
            copyCtr++;
        }
        while (j < right) {
            result[idx++] = nums[j++];
            copyCtr++;
        }
        copyCtr += (right - left);
        System.arraycopy(result, left, nums, left, right - left);
    }

}
