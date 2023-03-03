//package com.example.zhangtuo.learndeme;
//
///**
// * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
// *
// * 输入：l1 = [1,2,4], l2 = [1,3,4]
// * 输出：[1,1,2,3,4,4]
// *
// * @author zhangtuo
// * @date 2023/2/27
// */
//
//class Coding {
//    Node a = constructNodeA();
//    Node b = constructNodeB();
//    public static void main(String[] args) {
//        multiArray(a,b);
//    }
//
//    private static void multiArray(Node a, Node b) {
//        Node node = new Node(-1);
//
//        while(a!= null || b!= null){
//            if(a.data < b.data){
//                node.next = a;
//                a = a.next;
//                if(b){
//
//                }
//
//            }else{
//                node.next = b;
//            }
//
//            a = a.next;
//        }
//    }
//
//    private Node constructNodeA(){
//        Node a = new Node(1);
//        Node b = new Node(2);
//        Node c = new Node(4);
//        a.next = b;
//        b.next = c;
//        return a;
//    }
//
//    private Node constructNodeB(){
//        Node a = new Node(1);
//        Node b = new Node(3);
//        Node c = new Node(4);
//        a.next = b;
//        b.next = c;
//        return a;
//    }
//}
//
//class Node{
//    public int data;
//    public Node next;
//    public Node(int Data){
//        this.data = data;
//    }
//}
