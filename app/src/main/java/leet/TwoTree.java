package leet;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.two_tree.Tree;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/21
 */

class TwoTree {
   public static void main(String[] args) {

      //构造二叉树
      TreeNode root = new TreeNode(1);
      root.left = new TreeNode(2);
      root.right = new TreeNode(3);

      root.left.left = new TreeNode(4);
      root.left.right = new TreeNode(5);

      root.right.left = new TreeNode(6);
      root.right.right = new TreeNode(7);

      /**
       *       1
       *    2     3
       *  4  5  6   7
       */
      //层次（广度优先遍历）
      levelTraversal(root);
      //前序（深度优先遍历）
//      preOrderTraversal2(root);

   }

   /**
    * 前序：迭代方式
    * @param root
    */
   public static void preOrderTraversal2(TreeNode root) {
      LinkedList<TreeNode> stack = new LinkedList<>();
      TreeNode pNode = root;
      while (pNode != null || !stack.isEmpty()) {
         if (pNode != null) {
            System.out.print(pNode.data+"  ");
            stack.push(pNode);
            pNode = pNode.left;
         } else { //pNode == null && !stack.isEmpty()
            TreeNode node = stack.pop();
            pNode = node.right;
         }
      }
   }

   private static void preOrderTraversal(TreeNode root) {
      if (root == null){
         return;
      }
      System.out.println(root.data);
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
   }

   private static void levelTraversal(TreeNode root) {
      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.offer(root);
      while(!queue.isEmpty()){
         TreeNode node = queue.poll();
         System.out.println(node.data);
         if(node.left != null){
            queue.offer(node.left);
         }
         if (node.right != null){
            queue.offer(node.right);
         }
      }
   }

   static class TreeNode{
      public int data;
      public TreeNode left;
      public TreeNode right;

      public TreeNode(int data) {
         this.data = data;
      }
   }
}
