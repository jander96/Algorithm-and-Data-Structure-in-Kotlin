package queues

import Queue
import StackImpl
import example

class StackQueue<T: Any> : Queue<T> {
    private val leftStack = StackImpl<T>()
    private val rightStack = StackImpl<T>()
    override val count: Int
        get() = rightStack.count + leftStack.count

    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)
        return true
    }

    override fun dequeue(): T? {
        if(leftStack.isEmpty) transferElements()
        return leftStack.pop()
    }


    override fun peek(): T? {
        if(leftStack.isEmpty) transferElements()
        return leftStack.peek()
    }

    private fun transferElements() {
        // Expensive O(n) operation
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override fun toString(): String {
        return "Left stack: \n$leftStack \n Right stack:\n$rightStack"
    }
}

fun main(){
    "Queue with Double Stack" example {
        val queue = StackQueue<String>().apply {
            enqueue("Ray")
            enqueue("Brian")
            enqueue("Eric")
        }
        println(queue)
        queue.dequeue()
        println(queue)
        queue.enqueue("Rigobert")
        queue.enqueue("Annet")
        println(queue)
        println("Next up: ${queue.peek()}")
        queue.dequeue()
        println("Next up: ${queue.peek()}")
        queue.dequeue()
        println("Next up: ${queue.peek()}")
        queue.dequeue()
        println("Next up: ${queue.peek()}")

    }
}