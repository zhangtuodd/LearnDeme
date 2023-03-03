package leet.lru

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/17
 */
object hashmap_lru1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val lru = Lru1(5)
        lru.put("a","a")
        lru.put("b","b")
        lru.put("c","c")
        lru.put("d","d")
        lru.put("e","e")
        lru.get("c")
        lru.put("a","--")
        lru.iterate()
    }
}

class Lru1(val size: Int) {
    val head = DoubleLink()
    val tail = DoubleLink()
    val map = hashMapOf<String, DoubleLink>()

    init {
        head.next = tail
        tail.pre = head
    }

    fun iterate() {
        var temp:DoubleLink? = head
        while (temp?.next?.k != null){
            println("k:${temp.next?.k}  v:${temp.next?.v}")
            temp = temp.next
        }
    }

    fun put(k: String, v: String) {
        map[k].let {
            if (it == null) { //新增
                if (map.size >= size) {//满，删除尾部，添加到头部
                    val node = DoubleLink(k, v)
                    removeLast()
                    moveToHead(node)
                } else { //没满，添加到头部
                    val node = DoubleLink(k, v)
                    moveToHead(node)
                }
            } else {//更新  删除元素 ， 更新值 ， 添加到头部
                removeNode(it)
                it.v = v
                moveToHead(it)
            }
        }
    }

    fun get(k: String): String? {
        map[k].let {
            if (it == null) {
                println("get(${k}) = null")
                return null
            } else { //找到，删除元素 ， 添加到头部
                removeNode(it)
                moveToHead(it)
                println("get(${k}) = ${it.v}")
                return it.v
            }
        }
    }

    fun moveToHead(node: DoubleLink) {
        map[node.k!!] = node
        val next = head.next
        head.next = node
        node.pre = head
        node.next = next
        next?.pre = node
    }

    fun removeLast() {
        map.remove(tail.pre?.k)
        val pre = tail.pre?.pre
        pre?.next = tail
        tail.pre = pre
    }

    fun removeNode(node: DoubleLink) {
        map.remove(node.k)
        val pre = node.pre
        val next = node.next
        pre?.next = next
        next?.pre = pre
    }
}

class DoubleLink(
    var k: String? = null,
    var v: String? = null,
    var pre: DoubleLink? = null,
    var next: DoubleLink? = null
)

