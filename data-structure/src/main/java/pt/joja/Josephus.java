package pt.joja;

public class Josephus {

    public static void main(String[] args) {
        josephus(5, 2);
    }

    /**
     * 约瑟夫问题
     * 
     * @param n 人数
     * @param m 每次数的人数
     */
    public static void josephus(int n, int m) {
        CircleList circleList = new CircleList();
        for (int i = 0; i < n; i++) {
            circleList.add(new Node(i + 1));
        }
        circleList.list();
        while (!circleList.isEmpty()) {
            circleList.offsetHead(m - 1);
            Node out = circleList.popHead();
            System.out.println(out);
        }
    }

}

class CircleList {

    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        circleList.add(new Node(0));
        circleList.add(new Node(1));
        circleList.add(new Node(2));
        circleList.add(new Node(3));
        circleList.add(new Node(4));
        circleList.add(new Node(5));
        circleList.list();
        // offsetHead 2
        circleList.offsetHead(2);
        circleList.list();
        // popHead
        while (!circleList.isEmpty()) {
            System.out.println(circleList.popHead());
        }
    }

    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

    public Node popHead() {
        if (isEmpty()) {
            throw new RuntimeException("CircleList is empty.");
        }

        Node end = head;
        while (true) {
            if (end.next == head) {
                break;
            }
            end = end.next;
        }
        Node result = head;
        if (end == head) {
            head = null;
        } else {
            head = head.next;
            end.next = head;
        }
        return result;
    }

    public void offsetHead(int k) {
        if (head == null) {
            return;
        }

        Node curr = head;
        for (int i = 0; i < k; i++) {
            curr = curr.next;
        }
        head = curr;
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
            node.next = node;
        }

        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }

        temp.next = node;
        node.next = head;
    }

    public void list() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[ ");
        Node curr = head;
        while (true) {
            sb.append(curr);
            if (curr.next == head) {
                sb.append(" ]");
                return sb.toString();
            }
            sb.append(", ");
            curr = curr.next;
        }
    }

}

class Node {

    int no;

    Node next;

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node: { no: " + no + " }";
    }

}
