package pt.joja;

public class JojaDoubleLinkedList {

    public static void main(String[] args) {
        JojaDoubleLinkedList doubleLinkedList = new JojaDoubleLinkedList();
        doubleLinkedList.add(new DoubleHeroNode(1, "宋江", "及时雨"));
        doubleLinkedList.add(new DoubleHeroNode(3, "吴用", "智多星"));
        doubleLinkedList.add(new DoubleHeroNode(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.add(new DoubleHeroNode(4, "林冲", "豹子头"));
        doubleLinkedList.list();
    }

    // 头节点
    DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    public void add(DoubleHeroNode heroNode) {
        DoubleHeroNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = heroNode;
        heroNode.prev = curr;
    }

    public void list() {
        DoubleHeroNode curr = head.next;
        System.out.println("JojaLinkedList: [");
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
        System.out.println("]");
    }

}

class DoubleHeroNode {

    int no;

    String name;

    String nickName;

    DoubleHeroNode next;

    DoubleHeroNode prev;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DoubleHeroNode: { ");
        sb.append("no: " + no);
        sb.append(", name: " + name);
        sb.append(", nickName: " + nickName);
        sb.append(" }");
        return sb.toString();
    }

}
