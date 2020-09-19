package pt.joja;

public class OrderedJojaLinkedList extends JojaLinkedList {
    
    public static void main(String[] args) {
        JojaLinkedList linkedList = new OrderedJojaLinkedList();
        linkedList.add(new HeroNode(4, "林冲", "豹子头"));
        linkedList.add(new HeroNode(1, "宋江", "及时雨"));
        linkedList.add(new HeroNode(3, "吴用", "智多星"));
        linkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
        linkedList.add(new HeroNode(2, "卢俊义2", "玉麒麟"));
        linkedList.add(new HeroNode(2, "卢俊义3", "玉麒麟"));
        linkedList.list();
        linkedList.reverse();
        linkedList.list();
    }

    @Override
    public void add(HeroNode heroNode) {
        HeroNode curr = head;
        while(curr.next != null) {
            if (curr.next.no > heroNode.no) {
                heroNode.next = curr.next;
                curr.next = heroNode;
                return;
            }
            curr = curr.next;
        }
        if (curr.no < heroNode.no) {
            curr.next = heroNode;
        }
    }
    
}
