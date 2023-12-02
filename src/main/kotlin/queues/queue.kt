
infix fun String.exampleQueue(block: () -> Unit) {
    println("*************** $this *************** ")
    block()
    println("\n")
}

interface Queue<T> {
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun peek(): T?
}


class ArrayListQueue<T>: Queue<T>{
    private val list = arrayListOf<T>()

    override val count: Int
        get() = list.size

    override fun enqueue(element: T): Boolean = list.add(element)
    

    override fun dequeue(): T? = list.removeFirstOrNull()
    

    override fun peek(): T? = list.getOrNull(0)

    
    override fun toString(): String = list.toString()
}

class LinkedListQueue<T> : Queue<T> {
    private val list = LinkedListMutableCollection<T>()
    private var size = 0
    override fun enqueue(element: T): Boolean {
        list.append(element)
        size ++
        return true
    }

    override fun dequeue(): T? {
        val result = list.pop()
        size --
        return result
    }

    override val count: Int
        get() = size

    override fun peek(): T? {
        return list.firstOrNull()
    }

    override fun toString(): String {
        return list.toString()
    }
}

fun main(){
    "Queue with ArrayList" exampleQueue {
        val queue = ArrayListQueue<String>().apply {
        enqueue("Ray")
        enqueue("Brian")
        enqueue("Eric")
        }
        println(queue)
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")
    }

    "Queue with Doubly Linked List" example {
        val queue = LinkedListQueue<String>().apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }

        println(queue)
        queue.dequeue()
        println(queue)
        println("Next up: ${queue.peek()}")
    }
}