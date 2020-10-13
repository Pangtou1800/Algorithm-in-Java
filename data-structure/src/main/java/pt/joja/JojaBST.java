package pt.joja;

public class JojaBST<K extends Comparable<K>, V> {

    public static void main(String[] args) {
        int[] nums = { 7, 3, 10, 12, 5, 1, 9 };

        JojaBST<Integer, String> bst = new JojaBST<>();
        for (int num : nums) {
            bst.put(num, "val" + num);
        }

        bst.printInOrder();

        // while(!bst.isEmpty()) {
        //     bst.deleteMin();
        //     bst.printInOrder();
        //     System.out.println("----------");
        // }

        for (int num : nums) {
            bst.delete(num);
            bst.printInOrder();
            System.out.println("----------");
        }
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println(root);
        } else {
            printInOrder(root);
        }
    }

    private void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.println(node);
        printInOrder(node.right);
    }

    private TreeNode root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(TreeNode node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.val;
        } else if (node.key.compareTo(key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private TreeNode put(TreeNode root, K key, V val) {
        if (root == null) {
            return new TreeNode(key, val);
        }
        int comp = key.compareTo(root.key);
        if (comp < 0) {
            root.left = put(root.left, key, val);
        } else if (comp > 0) {
            root.right = put(root.right, key, val);
        } else {
            root.val = val;
        }
        return root;
    }

    public K min() {
        return min(root).key;
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode node, K key) {
        if (node == null) {
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = delete(node.left, key);
        } else if (comp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            TreeNode temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private class TreeNode {
        V val;
        K key;
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "{ " + key + " : " + val + " }";
        }
    }

}
