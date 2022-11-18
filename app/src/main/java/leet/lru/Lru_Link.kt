package leet.lru

/**
 * 描述信息
 *
 * 单链表实现lru
 *  - 都以data来传值
 *  -  get put时都添加到链头
 *  - 构造值为null的 head tail
 *  - del delLast moveToHead  findCur
 *
 * @author zhangtuo
 * @date 2022/11/16
 */
object Lru_Link {
    @JvmStatic
    fun main(args: Array<String>) {
        val lru = LruCache(5)
        lru.put(1)
        lru.put(2)
        lru.put(3)
        lru.put(4)
        lru.put(3)
        lru.put(5)
        lru.get(1)
        lru.put(6)
        lru.iterate()
    }


    class LruCache(val cap: Int) {
        var realSize = 0
        var head = Node(null)
        var tail = Node(null)

        init {
            head.next = tail
        }


        fun get(data: Int) {
            val find = findCur(data)
            if (find != null) {
                //删除节点，添加节点到头部
                del(data)
                moveToHead(data)
            } else {
                //nothing
            }
        }

        fun put(data: Int) {
            val find = findCur(data)
            if (find != null) {
                //删除节点，添加到头部
                del(data)
                moveToHead(data)
            } else {
                if (isFull()) {
                    //删除尾部节点，并添加当前节点到头部
                    delLast()
                    moveToHead(data)
                } else {
                    //添加节点到头部
                    moveToHead(data)
                    realSize++
                }
            }
        }

        fun del(data: Int) {
            var temp: Node? = head
            while (temp != null) {
                if (temp.next?.data == data) {
                    val next = temp.next?.next
                    temp.next = next
                }
                temp = temp.next
            }
        }

        fun delLast() {
            var temp: Node? = head
            while (temp != null) {
                if (temp.next?.data != null && temp.next?.next?.data == null) {
                    temp.next = temp.next?.next
                    break
                }
                temp = temp.next
            }
        }

        fun moveToHead(data: Int) {
            val next = head.next
            val node = Node(data)
            node.next = next
            head.next = node
        }

        fun findCur(data: Int): Node? {
            var cur: Node? = head
            while (cur != null) {
                if (cur.data == data) {
                    return cur
                }
                cur = cur.next
            }
            return null
        }

        fun isFull() = realSize >= cap

        fun iterate() {
            var temp: Node? = head
            while (temp != null) {
                print("${temp.data} -> ")
                temp = temp.next
            }
        }


    }
}


class Node(val data: Int?) {
    var next: Node? = null
}