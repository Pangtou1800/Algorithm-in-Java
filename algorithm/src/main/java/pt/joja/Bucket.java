package pt.joja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bucket {

    static int scale = 1000;
    static List<List<Integer>> buckets = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }
    }

    public static void main(String[] args) {
        int nums = 100;

        Random rand = new Random(47);
        int[] eles = new int[nums];
        for (int i = 0; i < nums; i++) {
            eles[i] = rand.nextInt(scale);
        }

        System.out.println(Arrays.toString(eles));
        sort(eles);
        System.out.println(Arrays.toString(eles));
    }

    public static void sort(int[] nums) {
        int copyCtr = 0;
        for (int currScale = 1; currScale < scale; currScale *= 10) {
            for (int i = 0; i < nums.length; i++) {
                buckets.get((nums[i] / currScale) % 10).add(nums[i]);
                copyCtr++;
            }
            int idx = 0;
            for (List<Integer> bucket : buckets) {
                for (int num : bucket) {
                    nums[idx++] = num;
                    copyCtr++;
                }
                bucket.clear();
            }
        }
        System.out.println("[Bucket.sort] copied  : " + copyCtr);
    }
}
