import kotlin.text.buildString

infix fun String.stackExample(block: ()->Unit){
    println("*************** $this *************** ")
    block()
    println("\n")
}

interface Stack<Element>{
    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0


    //Escential operations 
    fun push(element: Element)
    fun pop(): Element?

    //Non-essential operations
    fun peek(): Element?

}

class StackImpl<T:Any>(): Stack<T>{
    
    companion object {
        fun<T: Any> create(items: Iterable<T>): Stack<T>{
            val stack = StackImpl<T>()
            items.forEach{stack.push(it)}
            return stack
        }
    }

    private val storage = arrayListOf<T>()
    override val count: Int
        get()= storage.size

    override fun toString() = buildString{
        appendLine("----top----")
        storage.asReversed().forEach { 
            appendLine("$it")
        }
        appendLine("-----------")
    }

    override fun push(element: T){
        storage.add(element)
    }

    override fun pop(): T?{
        if(isEmpty) return null
        return storage.removeAt(storage.size -1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }
}

fun <T: Any> stackOf(vararg elements: T): Stack<T> {
    return StackImpl.create(elements.asList())
}

fun main() {
    "using a stack" stackExample
            {
                val stack =
                        StackImpl<Int>().apply {
                            push(1)
                            push(2)
                            push(3)
                            push(4)
                        }
                print(stack)
                val poppedElement = stack.pop()
                if (poppedElement != null) {
                    println("Popped: $poppedElement")
                }
                print(stack)
            }

    "initializing a stack from a list" stackExample
            {
                val list = listOf("A", "B", "C", "D")
                val stack = StackImpl.create(list)
                print(stack)
                println("Popped: ${stack.pop()}")
            }

    "initializing a stack from an array literal" stackExample
            {
                val stack = stackOf(1.0, 2.0, 3.0, 4.0)
                print(stack)
                println("Popped: ${stack.pop()}")
            }
}


