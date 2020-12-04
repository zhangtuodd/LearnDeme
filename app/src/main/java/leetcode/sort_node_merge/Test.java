package leetcode.sort_node_merge;

import leetcode.link_revert.Node;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-11-27
 */
public class Test {


    static NodeList nodeList = new NodeList(0);


    public static void main(String[] args) {


        NodeList oneNode = new NodeList(0);
        NodeList oneNode1 = new NodeList(1);
        NodeList oneNode2 = new NodeList(2);
        NodeList oneNode3 = new NodeList(3);
        NodeList oneNode4 = new NodeList(4);
        oneNode.setNext(oneNode1);
        oneNode1.setNext(oneNode2);
        oneNode2.setNext(oneNode3);
        oneNode3.setNext(oneNode4);

        NodeList twoNode = new NodeList(0);
        NodeList twoNode1 = new NodeList(2);
        NodeList twoNode2 = new NodeList(5);
        NodeList twoNode3 = new NodeList(7);
        NodeList twoNode4 = new NodeList(8);
        twoNode.setNext(twoNode1);
        twoNode1.setNext(twoNode2);
        twoNode2.setNext(twoNode3);
        twoNode3.setNext(twoNode4);

        merge(oneNode, twoNode);

    }

    /**
     * 两个链表从头到尾比较，结束条件为其中一个链表的下一个节点为空
     * @param oneNode
     * @param twoNode
     */
    private static void merge(NodeList oneNode, NodeList twoNode) {
        NodeList temp = nodeList;
        while (oneNode != null && twoNode != null) {
            if (oneNode.getData() < twoNode.getData()) {
                nodeList.setNext(oneNode);
                oneNode = oneNode.getNext();
            } else {
                nodeList.setNext(twoNode);
                twoNode = twoNode.getNext();
            }
            nodeList = nodeList.getNext();
            System.out.println(nodeList.getData());
        }

        if (oneNode == null) {
            nodeList.setNext(twoNode);
        }
        if (twoNode == null) {
            nodeList.setNext(oneNode);
        }


    }
}
