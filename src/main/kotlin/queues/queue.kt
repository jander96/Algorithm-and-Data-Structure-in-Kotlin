
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
}