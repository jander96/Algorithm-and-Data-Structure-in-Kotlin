
infix fun String.exampleIterable(block: () -> Unit) {
    println("*************** $this *************** ")
    block()
    println("\n")
}

data class NodeIterable<T>(var value: T, var next: NodeIterable<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedListIterable<T> : Iterable<T> {
    private var head: NodeIterable<T>? = null
    private var tail: NodeIterable<T>? = null
    var size = 0
        private set

    fun isEmpty() = size == 0

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        } else {
            return head.toString()
        }
    }

    fun push(value: T): LinkedListIterable<T> {
        head = NodeIterable(value = value, next = head)
        if (tail == null) tail = head
        size++
        return this
    }

    fun append(value: T): LinkedListIterable<T> {
        if (isEmpty()) return push(value)
        tail?.next = NodeIterable(value = value)
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): NodeIterable<T>? {
        var currentNode = head
        var currentIndex = 0

        while (index != currentIndex && currentNode != null) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: NodeIterable<T>): NodeIterable<T> {
        if (afterNode == tail) {
            append(value)
            return tail!!
        }
        val newNode = NodeIterable(value = value, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    // Removing

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    fun removeLast(): T? {
        val head = head ?: return null
        if (head.next == null) return pop()

        size--
        var prev = head
        var current: NodeIterable<T>? = head
        var next = current?.next

        while (current != null) {
            prev = current
            current = next
            next = current?.next
        }

        prev.next = null
        tail = prev
        return current?.value
    }

    fun removeAfter(node: NodeIterable<T>): T? {
        val result = node.next?.value
        if (node.next == tail) tail = node
        if (node.next != null) size--

        node.next = node.next?.next
        return result
    }

    override fun iterator(): Iterator<T> = LinkedListIterator(this)
}

class LinkedListIterator<T>(private val linkedList: LinkedListIterable<T>) : Iterator<T> {
    private var index = 0
    private var lastNode: NodeIterable<T>? = null

    override fun hasNext(): Boolean = index < linkedList.size

    override fun next(): T {

        if (index >= linkedList.size) throw IndexOutOfBoundsException()
        lastNode =
                if (index == 0) {
                    linkedList.nodeAt(0)
                } else {
                    lastNode?.next
                }
        index++
        return lastNode!!.value
    }
}

fun main() {
    "printing doubles" exampleIterable
            {
                val list = LinkedListIterable<Int>()
                list.push(3)
                list.push(2)
                list.push(1)
                println(list)
                for (item in list) {
                    println("Double: ${item * 2}")
                }
            }
            
}

