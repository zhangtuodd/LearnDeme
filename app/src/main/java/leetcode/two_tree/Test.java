package leetcode.two_tree;

import java.util.LinkedList;

/**
 * 二叉树层次遍历
 *
 * @author zhangtuo
 * @date 2020-11-27
 */
public class Test {
    /**
     * 1
     * 2    3
     * 4  5 6  7
     * 8    9
     *
     * @param args
     */
    public static void main(String[] args) {
        Tree tree = new Tree(1);
        tree.left = new Tree(2);
        tree.right = new Tree(3);
        tree.left.left = new Tree(4);
        tree.left.right = new Tree(5);
        tree.right.left = new Tree(6);
        tree.right.right = new Tree(7);

        traverse(tree, 1);
    }

    /**
     * 当前节点以及当前层数
     *
     * @param tree
     * @param level
     */
    private static void traverse(Tree tree, int level) {

        if (tree == null) return;
        LinkedList<Tree> list = new LinkedList<Tree>();
        list.add(tree);
        Tree currentNode;
        while (!list.isEmpty()) {
            currentNode = list.poll();
            System.out.println(currentNode.data);
            if (currentNode.left != null) {
                list.add(currentNode.left);
            }
            if (currentNode.right != null) {
                list.add(currentNode.right);
            }
        }
    }
}
