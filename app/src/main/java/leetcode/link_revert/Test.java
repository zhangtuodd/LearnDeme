package leetcode.link_revert;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-17
 */
public class Test {


    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        //打印原始链表
        Node n = head;
        while (n != null) {
            System.out.println("node----" + n.getData());
            n = n.getNext();
        }

        System.out.println("--------------------------------");

        if (head == null || head.getNext() == null) {
            return;
        }

        Node left = head;
        Node center = head.getNext();
        Node temp;
        while (center != null) {
            temp = center.getNext();
            center.setNext(left);
            left = center;
            center = temp;
        }
        head.setNext(null);
        Node k = left;
        while (k != null) {
            System.out.println("node****" + k.getData());
            k = k.getNext();
        }

        System.out.println("left****" + left.getData());


//        //反转链表
//        if (head == null) {
//            return;
//        }
//        Node node = revertLink(head);
//        head.setNext(null);
//        while (node != null) {
//            System.out.println("node+++" + node.getData());
//            node = node.getNext();
//        }

    }

    private static Node revertLink(Node head) {
        Node left = head;
        Node center = head.getNext();                 
        Node temp;
        while (center != null) {
            temp = center.getNext();
            center.setNext(left);
            left = center;
            center = temp;
        }
        return left;
    }
}
