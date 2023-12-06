package trees.binary_treee

import kotlin.math.max

typealias Visitor<T> = (T)->Unit

class BinaryNode<T>(val value: T){
    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    fun traverseInOrder(visit: Visitor<T>){
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: Visitor<T>){
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    fun traversePostOrder(visit: Visitor<T>){
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    fun height(node: BinaryNode<T>? = this): Int {
        return node?.let {
            1 + max(
                height(node.leftChild),
                height(node.rightChild)
            )
        } ?: -1
    }


    override fun toString() = diagram(this)

    private fun diagram(
        node: BinaryNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = ""
    ): String {
        return node?.let {
            if(node.leftChild == null && node.rightChild == null){
                "$root${node.value}\n"
            }else{
                diagram(node.rightChild,"$top ", "$top┌──","$top│ ")+
                        root+"${node.value}\n" + diagram(node.leftChild,
                    "$bottom| ", "$bottom└──","$bottom ")
            }
        } ?: "${root}null\n"
    }
}

fun main() {
    val zero = BinaryNode(0)
    val one = BinaryNode(1)
    val five = BinaryNode(5)
    val seven = BinaryNode(7)
    val eight = BinaryNode(8)
    val nine = BinaryNode(9)
    seven.leftChild = one
    one.leftChild = zero
    one.rightChild = five
    seven.rightChild = nine
    nine.leftChild = eight
    val tree = seven

    println(tree)
    tree.traverseInOrder { println(it) }
    println("########################")
    tree.traversePreOrder { println(it) }
    println("########################")
    tree.traversePostOrder { println(it) }
}

