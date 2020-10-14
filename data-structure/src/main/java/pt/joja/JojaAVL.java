package pt.joja;

public class JojaAVL<K extends Comparable<K>, V> {

    public static void main(String[] args) {
        int[] nums = { 4, 3, 6, 5, 7, 8 };
        nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11 };
        nums = new int[] { 10, 11, 7, 6, 8, 9 };
        nums = new int[] { 7, 2, 3, 4, 5, 6, 15, 8, 9, 10, 11, 12 };

        JojaAVL<Integer, String> avl = new JojaAVL<>();
        for (int num : nums) {
            avl.put(num, "Val" + num);
        }

        avl.printInOrder();

        System.out.println(avl.height(avl.root));
        System.out.println(avl.height(avl.root.left));
        System.out.println(avl.height(avl.root.right));

        // avl.root = avl.leftRotate(avl.root);

        // avl.printInOrder();

        // System.out.println(avl.height(avl.root));
        // System.out.println(avl.height(avl.root.left));
        // System.out.println(avl.height(avl.root.right));

    }

    // ----

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    // ----

    private Node root;

    public void printPreOrder() {
        if (root == null) {
            System.out.println("null");
        }
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node) {
        System.out.print("{");
        System.out.print(node);
        if (node.left != null) {
            System.out.print(",\"l\":");
            printPreOrder(node.left);
        }
        if (node.right != null) {
            System.out.print(",\"r\":");
            printPreOrder(node.right);
        }
        System.out.print("}");
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("null");
        }
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        System.out.print("{");
        if (node.left != null) {
            System.out.print("\"l\":");
            printInOrder(node.left);
            System.out.print(",");
        }
        System.out.print(node);
        if (node.right != null) {
            System.out.print(",\"r\":");
            printInOrder(node.right);
        }
        System.out.print("}");
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = put(node.left, key, val);
        } else if (comp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        if (height(node.right) - height(node.left) > 1) {
            if (node.right != null && height(node.right.left) > height(node.right.right)) {
                node.right = rightRotate(node.right);
            }
            node = leftRotate(node);
        }
        if (height(node.left) - height(node.right) > 1) {
            if (node.left != null && height(node.left.right) > height(node.left.left)) {
                node.left = leftRotate(node.left);
            }
            node = rightRotate(node);
        }

        return node;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "\"" + key + "\":\"" + val + "\"";
        }
    }

}
