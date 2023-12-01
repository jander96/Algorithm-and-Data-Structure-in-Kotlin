
infix fun String.exampleCollection(block: () -> Unit) {
    println("*************** $this *************** ")
    block()
    println("\n")
}

data class NodeCollection<T>(var value: T, var next: NodeCollection<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedListCollection<T> : Iterable<T>, Collection<T>, MutableIterable<T>{
    private var head: NodeCollection<T>? = null
    private var tail: NodeCollection<T>? = null
    override var size = 0
        private set
    

    override fun isEmpty() = size == 0

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        } else {
            return head.toString()
        }
    }

    fun push(value: T): LinkedListCollection<T> {
        head = NodeCollection(value = value, next = head)
        if (tail == null) tail = head
        size++
        return this
    }

    fun append(value: T): LinkedListCollection<T> {
        if (isEmpty()) return push(value)
        tail?.next = NodeCollection(value = value)
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): NodeCollection<T>? {
        var currentNode = head
        var currentIndex = 0

        while (index != currentIndex && currentNode != null) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: NodeCollection<T>): NodeCollection<T> {
        if (afterNode == tail) {
            append(value)
            return tail!!
        }
        val newNode = NodeCollection(value = value, next = afterNode.next)
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
        var current: NodeCollection<T>? = head
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

    fun removeAfter(node: NodeCollection<T>): T? {
        val result = node.next?.value
        if (node.next == tail) tail = node
        if (node.next != null) size--

        node.next = node.next?.next
        return result
    }

    // Iterable override
    override fun iterator(): MutableIterator<T> = LinkedListIteratorCollection(this)

    
    // Collections overrides
    override fun contains(element: T): Boolean { 
        for(item in this){
            if(item == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean { 
        if(size == 0) return false

        elements.forEach{ element->
            if(!contains(element)) return false
        }
        return true
    }
}

class LinkedListIteratorCollection<T>(private val linkedList: LinkedListCollection<T>) :
        MutableIterator<T> {
    private var index = 0
    private var lastNode: NodeCollection<T>? = null

    override fun hasNext(): Boolean = index < linkedList.size

    override fun next(): T {

        if (index >= linkedList.size) throw IndexOutOfBoundsException()
        lastNode =
                if (index == 0) { // -> estado por defecto. No ha empezado a iterar
                    linkedList.nodeAt(0) // -> index = 0 en nodeAt() corresponde al node **head**
                } else {
                    lastNode?.next
                }
        index++
        return lastNode!!.value
    }

    override fun remove() {
        if(index == 1){ 
            linkedList.pop()
        }else{
            val prevNode = linkedList.nodeAt(index-2) ?: return 
            linkedList.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }
}


fun main() {
    "printing doubles" exampleCollection
            {
                val list = LinkedListCollection<Int>()
                list.push(3)
                list.push(2)
                list.push(1)
                println(list)
                for (item in list) {
                    println("Double: ${item * 2}")
                }
            }
            
}

