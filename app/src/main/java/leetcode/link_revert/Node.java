package leetcode.link_revert;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-17
 */
public class Node {

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
