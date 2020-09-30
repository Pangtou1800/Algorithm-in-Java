package pt.joja;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInArray {

    public static void main(String[] args) {
        int[] eles = { 1, 2, 3, 4, 5, 6, 7 };
        BinaryTreeInArray tree = new BinaryTreeInArray(eles);
        System.out.println(tree.preOrder());
        System.out.println(tree.inOrder());
        System.out.println(tree.postOrder());
    }

    private int[] eles;

    public BinaryTreeInArray(int[] eles) {
        this.eles = eles;
    }

    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(result, 0);
        return result;
    }

    public void preOrder(List<Integer> result, int currNode) {
        if (currNode >= eles.length) {
            return;
        }
        result.add(eles[currNode]);
        preOrder(result, 2 * currNode + 1);
        preOrder(result, 2 * currNode + 2);
    }

    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrder(result, 0);
        return result;
    }

    public void inOrder(List<Integer> result, int currNode) {
        if (currNode >= eles.length) {
            return;
        }
        inOrder(result, 2 * currNode + 1);
        result.add(eles[currNode]);
        inOrder(result, 2 * currNode + 2);
    }

    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrder(result, 0);
        return result;
    }

    public void postOrder(List<Integer> result, int currNode) {
        if (currNode >= eles.length) {
            return;
        }
        postOrder(result, 2 * currNode + 1);
        postOrder(result, 2 * currNode + 2);
        result.add(eles[currNode]);
    }
}
