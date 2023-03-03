package leet

/**
 *
 *
 * @author zhangtuo
 * @date 2022/10/20
 *
 *  LeetCode 206，题目是一个基础，反转单链表。+
 *  LeetCode 234，回文链表，其中有一个解法是需要将链表反转的，在你学习完206，再来练习234，是一种很好的巩固方法。
 *  LeetCode 92，题目要求反转链表中的一部分。
 *  LeetCode 24，链表两两交换结点，相当于是反转两个结点。+
 *  LeetCode 25，k 个一组反转链表，链表的两个 hard 之一。如果你掌握了 206，92 和 24，这个 hard 的难度其实没那么大。
 */
internal object Leet_ListNode {
    @JvmStatic
    fun main(args: Array<String>) {
//        //删除链表中的某一部分
//        val head = ListNode("a")
//        val node1 = ListNode("b")
//        val node2 = ListNode("c")
//        val node3 = ListNode("d")
//        val node4 = ListNode("e")
//        val node5 = ListNode("f")
//
//        head.next = node1
//        node1.next = node2
//        node2.next = node3
//        node3.next = node4
//        node4.next = node5
//        node5.next = null
//        delSomeOneInListNode(head,node2)

        val list: MutableList<String> = ArrayList()
        list.add("沉默王二")
        list.add("沉默王三")
        list.add("一个文章真特么有趣的程序员")
        for (i in 0 until list.size) {
            val str = list[i]
            if ("沉默王二" == str) {
                list.remove(str)
            }
        }
        System.out.println(list.toString())


        //反转全链表
        reverseListNode(initListNode())
//        val recursion = reverseRecursion(initListNode())//递归。。
//        iterateListNode(recursion)

        //反转链表中的一部分
//        reversePartListNode(initListNode(), 5, 8)

        //反转相邻节点
//        reverseListNodePerTwo(initListNode())

        //k个一组反转链表
//        reverseListNodeTrans(initListNode(), 3)
    }

    /**
     * 删除链表中的某个元素并输出新链表
     */
    fun delSomeOneInListNode(head: ListNode?, delNode: ListNode?) {
        var curr = head
        var temp = head
        while (curr != null) {
            temp = curr.next
            if (temp == delNode && delNode != null) {
                curr.next = delNode.next
                break
            }
            curr = temp
        }
        iterateListNode(head)
    }


    /**
     * LeetCode 206，题目是一个基础，反转单链表。
     * 链表反转（循环）
     *  1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3
     */
    fun reverseListNode(head: ListNode?) {
        var prev: ListNode? = null
        var curr = head
        while (curr != null) {
            var next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        iterateListNode(prev)
    }

    /**
     * 链表反转（递归）
     */
    fun reverseRecursion(head: ListNode?): ListNode? {
        if (head!!.next == null) return head
        val last = reverseRecursion(head.next)
        head.next!!.next = head
        head.next = null
        return last
    }


    /**
     * 反转从位置 m到n的链表。请使用一趟扫描完成反转。 说明： 1 <= m <= n ≤链表长度。
     * 示例：输入：1->2->3->4->5->NULL,m=2，n= 4 输出：1->4->3->2->5->NULL
     *
     * 切割为3部分。中间反转部分和正常链表反转一样需要构造prev=null，反转完成后剩余部分就是curr
     * part1是尾部
     * part2是头部，因此在拼接时需要遍历到不为空的尾部 再进行指向
     *
     */
    fun reversePartListNode(head: ListNode?, m: Int, n: Int) {
        var curr = head
        var prev: ListNode? = null
        var index = 1
        //先计算区间外的前半部分
        while (curr != null && index < m) {
            index += 1
            prev = curr
            curr = curr.next
        }
        if (index > n) {
            iterateListNode(head)
            return
        }

        var part1 = prev
        var part2Prev: ListNode? = null
        //反转部分
        while (index in m..n && curr != null) {
            val next = curr.next
            curr.next = part2Prev
            part2Prev = curr
            curr = next
            index += 1
        }

        //拼接部分
        var part2Head = part2Prev
        if (part1 == null) {
            if (curr != null) {
                while (part2Prev?.next != null) {
                    part2Prev = part2Prev.next
                }
                part2Prev?.next = curr
            }
            iterateListNode(part2Head)
        } else {
            part1.next = part2Head
            if (curr != null) {
                while (part1?.next != null) {
                    part2Prev = part1.next
                }
                part2Prev?.next = curr
            }
        }
        iterateListNode(head)

    }

    /**
     * 链表中相邻两个节点反转
     *
     * -1abc - -1bac
     * 占位节点用来记录头节点
     * 中间两个节点 + 占位节点 用来重新指向
     * 第4个节点用来暂存
     * prev：第一轮中以-1为头节点交换了ab指向，一轮结束后下一轮cd需要交换指针，因此新一轮是用a作为prev
     */
    fun reverseListNodePerTwo(head: ListNode?) {
        val holder = ListNode("-1")
        holder.next = head
        var prev: ListNode? = holder
        while (prev?.next != null && prev.next?.next != null) {
            val node1 = prev.next // a
            val node2 = prev.next!!.next //b
            val next = node2!!.next //c
            node1!!.next = next //a-c
            node2.next = node1 //b-a
            prev.next = node2 //-1-b
            prev = node1
        }
        iterateListNode(holder.next)
    }

    /**
     * 反转链表变种1:
     * 链表中每K个节点进行一次反转
     * - 长度大于K
     * - 不是K的整数倍剩余节点保持顺序不变
     * 1 → 2 → 3 → 4 - 5，k=2我们想要把它改成 3-2-1-4-5
     *
     */
    fun reverseListNodeTrans(head: ListNode?, transLimit: Int) {
        //分割 （等于limit则反转，小于则不变，记下子链表头尾）
        var limitTemp = 1
        var subNode = head
        var subNodeTemp = head
        while (limitTemp < transLimit && subNode != null) {
            limitTemp += 1
            subNode = subNode.next
        }

        System.out.println(">>>>$subNode")
        limitTemp = 0


        fun dealSplit(head: ListNode?) {

        }
    }


    //base:循环输出链表数据
    fun iterateListNode(node: ListNode?) {
        var temp: ListNode? = node
        while (temp != null) {
            System.out.print("${temp.data} > ")
            temp = temp.next
        }
        System.out.print("${temp} > \n ----------------")

    }

    //base:初始化链表数据
    fun initListNode(): ListNode {
        val head = ListNode("a")
        val node1 = ListNode("b")
        val node2 = ListNode("c")
        val node3 = ListNode("d")
        val node4 = ListNode("e")
        val node5 = ListNode("f")

        head.next = node1
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = null
        return head
    }
}