package leet;

import leet.lru.Node;

/**
 * 链表反转
 *
 * @author zhangtuo
 * @date 2023/2/20
 */

class NodeRevert {


    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        Node revert = revert(node);
        while (revert != null) {
            System.out.println(revert.getData());
            revert = revert.getNext();
        }
    }

    /**
     * 原始数据：
     * a->b->c->d
     * 1：b->a c->d
     * c->b->a->d
     * d->c->b->a
     *
     * @param node
     */
    private static Node revert(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }
        Node left = node;
        Node center = node.getNext();
        Node right;
        while (center != null) {
            right = center.getNext();   // right  3->4  4
            center.setNext(left);       // center 2->1
//            if (left == node){
//                left.setNext(null);
//            }
            left = center;              // left   2->1
            center = right;             // center 3->4

        }
        node.setNext(null);
        return left;
    }
}
