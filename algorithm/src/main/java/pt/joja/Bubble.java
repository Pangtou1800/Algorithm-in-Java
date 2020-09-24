package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class Bubble {

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
        System.out.println("------------------------");
        System.out.println(Arrays.toString(eles));
        sortWithOutJudge(eles2);
        System.out.println(Arrays.toString(eles));
    }

    public static void sort(int[] eles) {
        int compCtr = 0;
        int swapCtr = 0;
        for (int i = eles.length; i > 0; i--) {
            // 一次排序中没有交换则已经有序
            // 实测这优化非常鸡肋
            // 开销甚至可能抵消了性能
            boolean moved = false;
            for (int j = 1; j < i; j++) {
                compCtr++;
                if (eles[j - 1] > eles[j]) {
                    swapCtr++;
                    moved = true;
                    int temp = eles[j - 1];
                    eles[j - 1] = eles[j];
                    eles[j] = temp;
                }
            }
            if (!moved) {
                break;
            }
        }
        System.out.println("[Bubble.sort] compared: " + compCtr);
        System.out.println("[Bubble.sort] swaped  : " + swapCtr);
    }

    public static void sortWithOutJudge(int[] eles) {
        int compCtr = 0;
        int swapCtr = 0;
        for (int i = eles.length; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                compCtr++;
                if (eles[j - 1] > eles[j]) {
                    swapCtr++;
                    int temp = eles[j - 1];
                    eles[j - 1] = eles[j];
                    eles[j] = temp;
                }
            }
        }
        System.out.println("[Bubble.sort] compared: " + compCtr);
        System.out.println("[Bubble.sort] swaped  : " + swapCtr);
    }
}
