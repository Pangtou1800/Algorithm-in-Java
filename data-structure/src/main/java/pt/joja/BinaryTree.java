package pt.joja;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    // 4
    // 2 6
    // 1 3 5 7

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(root.preOrder());
        System.out.println(root.inOrder());
        System.out.println(root.postOrder());

        System.out.println("[PreSearching] : " + 0);
        root.preSearch(0);
        System.out.println("[InSearching] : " + 0);
        root.inSearch(0);
        System.out.println("[PostSearching] : " + 0);
        root.postSearch(0);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public boolean preSearch(int val) {
        System.out.println("Comparing node " + this.val);
        if (this.val == val) {
            return true;
        }
        if (left != null) {
            if (left.preSearch(val)) {
                return true;
            }
        }
        if (right != null) {
            if (right.preSearch(val)) {
                return true;
            }
        }
        return false;
    }

    public boolean inSearch(int val) {
        if (left != null) {
            if (left.inSearch(val)) {
                return true;
            }
        }
        System.out.println("Comparing node " + this.val);
        if (this.val == val) {
            return true;
        }
        if (right != null) {
            if (right.inSearch(val)) {
                return true;
            }
        }
        return false;
    }

    public boolean postSearch(int val) {
        if (left != null) {
            if (left.postSearch(val)) {
                return true;
            }
        }
        if (right != null) {
            if (right.postSearch(val)) {
                return true;
            }
        }
        System.out.println("Comparing node " + this.val);
        if (this.val == val) {
            return true;
        }
        return false;
    }

    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(result);
        return result;
    }

    private void preOrder(List<Integer> result) {
        result.add(val);
        if (left != null) {
            left.preOrder(result);
        }
        if (right != null) {
            right.preOrder(result);
        }
    }

    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrder(result);
        return result;
    }

    private void inOrder(List<Integer> result) {
        if (left != null) {
            left.inOrder(result);
        }
        result.add(val);
        if (right != null) {
            right.inOrder(result);
        }
    }

    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrder(result);
        return result;
    }

    private void postOrder(List<Integer> result) {
        if (left != null) {
            left.postOrder(result);
        }
        if (right != null) {
            right.postOrder(result);
        }
        result.add(val);
    }

}
