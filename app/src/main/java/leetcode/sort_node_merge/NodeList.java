package leetcode.sort_node_merge;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-27
 */
public class NodeList {

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeList getNext() {
        return next;
    }

    public void setNext(NodeList next) {
        this.next = next;
    }

    private int data;

    private NodeList next;

    public NodeList(int data) {
        this.data = data;
    }
}
