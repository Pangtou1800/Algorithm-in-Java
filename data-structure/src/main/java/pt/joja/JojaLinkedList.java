package pt.joja;

import java.util.HashSet;
import java.util.Set;

public class JojaLinkedList {

    public static void main(String[] args) {
        JojaLinkedList linkedList = new JojaLinkedList();
        linkedList.add(new HeroNode(1, "宋江", "及时雨"));
        linkedList.add(new HeroNode(3, "吴用", "智多星"));
        linkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
        linkedList.add(new HeroNode(4, "林冲", "豹子头"));
        linkedList.list();
    }

    // 先初始化一个头节点
    protected HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单链表
    public void add(HeroNode heroNode) {
        HeroNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = heroNode;
        if (heroNode.next != null) {
            if (!validate()) {
                System.out.println("添加节点失败：链表中出现了循环");
                curr.next = null;
            }
        }
    }

    public void reverse() {
        HeroNode nodeL = head.next;
        if (nodeL == null || nodeL.next == null) {
            return;
        }
        
        HeroNode nodeR = nodeL.next;
        // 反转后最后一个节点next置空
        nodeL.next = null;

        while(nodeR != null) {
            HeroNode temp = nodeR.next;
            nodeR.next = nodeL;
            nodeL = nodeR;
            nodeR = temp;
        }

        head.next = nodeL;
    }

    public void list() {
        HeroNode curr = head.next;
        System.out.println("JojaLinkedList: [");
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
        System.out.println("]");
    }

    protected boolean validate() {
        Set<HeroNode> set = new HashSet<>();
        HeroNode curr = head;
        while (curr.next != null) {
            if (set.contains(curr)) {
                return false;
            }
            set.add(curr);
            curr = curr.next;
        }
        return true;
    }

}

class HeroNode {

    int no;

    String name;

    String nickName;

    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HeroNode: { ");
        sb.append("no: " + no);
        sb.append(", name: " + name);
        sb.append(", nickName: " + nickName);
        sb.append(" }");
        return sb.toString();
    }

}