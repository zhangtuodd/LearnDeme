package leet.lru

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/17
 */
object hashmap_lru {

    @JvmStatic
    fun main(args: Array<String>) {
        val lru = Lru(5)
        lru.put("a","a")
        lru.put("b","b")
        lru.put("c","c")
        lru.put("d","d")
        lru.put("e","e")
//        lru.get("c")
        lru.put("a","--")
        lru.iterate()
    }
}

class Lru(val size: Int) {
    val head = DoubleLinkedNode()
    val tail = DoubleLinkedNode()
    val map = HashMap<String, DoubleLinkedNode>()

    init {
        head.next = tail
        tail.pre = head
    }

    fun iterate(){
        var temp: DoubleLinkedNode? = head
        while (temp?.next?.k!= null){
            println("k:${temp.next?.k}  v:${temp.next?.v}")
            temp = temp.next
        }
    }

    fun put(k: String, v: String) {
        map[k].let {
            if (it == null) { // 新增 指针/map
                if (map.size >= size) {
                    removeLast()
                }
                val node = DoubleLinkedNode(k, v)
                addToHead(node)
                map.put(k,node)
            } else { // 更新 (移除元素指针变动，添加到头部) 指针/map
                it.v = v
                removeNode(it)
                addToHead(it)
                map.put(k,it)
            }
        }
    }


    fun get(k:String){
        map[k].let {
            if (it == null){
                println("get(${k}) = null")
            }else{
                removeNode(it)
                addToHead(it)
                println("get(${k}) = ${it.v}")
            }
        }
    }

    private fun removeLast() {
        val last = tail.pre
        map.remove(last?.k)
        last?.pre?.next = tail
        tail.pre = last?.pre
    }

    private fun addToHead(node: DoubleLinkedNode) {
        val next = head.next
        head.next = node
        node.pre = head
        node.next = next
        next?.pre = node
    }

    private fun removeNode(it: DoubleLinkedNode) {
        val pre = it.pre
        val next = it.next
        pre?.next = next
        next?.pre = pre
    }

}

class DoubleLinkedNode(
    var k: String? = null,
    var v: String? = null,
    var pre: DoubleLinkedNode? = null,
    var next: DoubleLinkedNode? = null
)