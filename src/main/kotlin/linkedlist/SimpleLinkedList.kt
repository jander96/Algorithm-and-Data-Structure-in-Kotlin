
infix fun String.example(block : ()->Unit){
    println("*************** $this *************** ")
    block()
    println("\n")
}



data class Node<T>(var value: T, var next: Node<T>? = null){
    override fun toString(): String{
        return if(next != null){
            "$value -> ${next.toString()}"
        }else{
            "$value"
        }
    }
}

class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty() = size == 0

    override fun toString(): String{
        if(isEmpty()){
            return "Empty list"
        }else{
            return head.toString()
        }
    }
    
    fun push(value: T): LinkedList<T>{
        head = Node(value=value,next= head)
        if(tail == null) tail = head
        size++
        return this
    }

    fun append(value: T): LinkedList<T>{
        if(isEmpty()) return push(value)
        tail?.next = Node(value = value)
        tail = tail?.next
        size++
        return this
    }

    fun nodeAt(index: Int): Node<T>?{
        var currentNode = head
        var currentIndex = 0

        while(index != currentIndex && currentNode != null){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T>{
        if(afterNode == tail){
            append(value)
            return tail!!
        }
        val newNode = Node(value = value, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    //Removing

    fun pop(): T?{
        if(!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if(isEmpty()){
            tail = null
        }
        return result
    }

    fun removeLast(): T?{
        val head = head ?: return null
        if(head.next == null) return pop()

        size--
        var prev = head
        var current: Node<T>? = head
        var next = current?.next

        while(current != null){
            prev = current
            current = next
            next = current?.next
        }

        prev.next = null
        tail = prev
        return current?.value
    }

    fun removeAfter(node: Node<T>): T?{
        val result = node.next?.value
        if(node.next == tail) tail = node
        if(node.next != null) size--

        node.next = node.next?.next
        return result

    }
}


fun main() {
    "creating and linking nodes" example
            {
                val node1 = Node(value = 1)
                val node2 = Node(value = 2)
                val node3 = Node(value = 3)
                node1.next = node2
                node2.next = node3
                println(node1)
            }

    "push" example
            {
                val list = LinkedList<Int>()
                list.push(3)
                list.push(2)
                list.push(1)
                println(list)
            }
    "fluent interface push" example
            {
                val list = LinkedList<Int>()
                list.push(3).push(2).push(1)
                println(list)
            }

    "append" example
            {
                val list = LinkedList<Int>()
                list.append(1)
                list.append(2)
                list.append(3)
                println(list)
            }
   "inserting at a particular index" example
        {
            val list = LinkedList<Int>()
            list.push(3)
            list.push(2)
            list.push(1)
            println("Before inserting: $list")
            var middleNode = list.nodeAt(1)!!
            for (i in 1..3) {
                middleNode = list.insert(-1 * i, middleNode)
            }
            println("After inserting: $list")
        }

"pop" example
        {
            val list = LinkedList<Int>()
            list.push(3)
            list.push(2)
            list.push(1)
            println("Before popping list: $list")
            val poppedValue = list.pop()
            println("After popping list: $list")
            println("Popped value: $poppedValue")
        }

        "removing a node after a particular node" example {
            val list = LinkedList<Int>()
            list.push(3)
            list.push(2)
            list.push(1)
            println("Before removing at particular index: $list")
            val index = 1
            val node = list.nodeAt(index - 1)!!
            val removedValue = list.removeAfter(node)
            println("After removing at index $index: $list")
            println("Removed value: $removedValue")
            }

}






