infix fun String.exampleMutableCollection(block: () -> Unit) {
    println("*************** $this *************** ")
    block()
    println("\n")
}

data class NodeMutableCollection<T>(var value: T, var next: NodeMutableCollection<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedListMutableCollection<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T>{
    private var head: NodeMutableCollection<T>? = null
    private var tail: NodeMutableCollection<T>? = null
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

    fun push(value: T): LinkedListMutableCollection<T> {
        head = NodeMutableCollection(value = value, next = head)
        if (tail == null) tail = head
        size++
        return this
    }

    fun append(value: T): LinkedListMutableCollection<T> {
        if (isEmpty()) return push(value)
        tail?.next = NodeMutableCollection(value = value)
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): NodeMutableCollection<T>? {
        var currentNode = head
        var currentIndex = 0

        while (index != currentIndex && currentNode != null) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: NodeMutableCollection<T>): NodeMutableCollection<T> {
        if (afterNode == tail) {
            append(value)
            return tail!!
        }
        val newNode = NodeMutableCollection(value = value, next = afterNode.next)
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
        var current: NodeMutableCollection<T>? = head
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

    fun removeAfter(node: NodeMutableCollection<T>): T? {
        val result = node.next?.value
        if (node.next == tail) tail = node
        if (node.next != null) size--

        node.next = node.next?.next
        return result
    }

    // Iterable override
    override fun iterator(): MutableIterator<T> = LinkedListIteratorMutableCollection(this)

    
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

    // MutableCollection overrides
    override fun add(element: T): Boolean { 
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean { 
        elements.forEach{value->
            append(value)
        }
        return true
    }

    override fun clear() { 
        head = null
        tail = null
        size = 0
    }


    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while(iterator.hasNext()){
            val item = iterator.next()

            if(element == item){
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean { 
        var result = false
        elements.forEach{element->
        result = remove(element) || result
        }
        return result
    }

override fun retainAll(elements: Collection<T>): Boolean {
    var result = false
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val item = iterator.next()
        if (!elements.contains(item)) {
            iterator.remove()
            result = true
        }
    }
    return result
}

}

class LinkedListIteratorMutableCollection<T>(private val linkedList: LinkedListMutableCollection<T>) :
        MutableIterator<T> {
    private var index = 0
    private var lastNode: NodeMutableCollection<T>? = null

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
    "removing elements" exampleMutableCollection {
        val list: MutableCollection<Int> = LinkedListMutableCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        println(list)
        list.remove(1)
        println(list)
        }
        "retaining elements" exampleMutableCollection {
        val list: MutableCollection<Int> = LinkedListMutableCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)
        println(list)
        list.retainAll(listOf(3, 4, 5))
        println(list)
        }
        "remove all elements" exampleMutableCollection {
        val list: MutableCollection<Int> = LinkedListMutableCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)
        println(list)
        list.removeAll(listOf(3, 4, 5))
        println(list)
        }
}

