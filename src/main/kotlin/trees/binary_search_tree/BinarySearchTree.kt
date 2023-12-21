package trees.binary_search_tree

import example
import trees.binary_treee.BinaryNode

class BinarySearchTree<T: Comparable<T>>() {
    var root: BinaryNode<T>? = null



    fun insert(value: T){
        root = insert(root, value)
    }


    private fun insert(
        node: BinaryNode<T>?,
        value: T
    ) : BinaryNode<T>{
        node ?: return  BinaryNode(value)
        if(value < node.value){
            node.leftChild = insert(node.leftChild, value)
        }else{
            node.rightChild = insert(node.rightChild, value)
        }
        return  node
    }

    fun contains(value: T): Boolean {
        var current = root

        while (current != null) {
            if (current.value == value) return true

            current =
                if (value < current.value) current.leftChild
                else current.rightChild
        }
        return false
    }

    fun remove(value: T){
        root = remove(root, value)
    }
    private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>?{

        node ?: return null

        when{
            value == node.value -> {
                // the node is a leaf node
                if (node.leftChild == null && node.rightChild == null) {
                    return null
                }

                // the node has no left child
                if(node.leftChild == null){
                    return node.rightChild
                }

                // the node has no right child
                if(node.rightChild == null){
                    return node.leftChild
                }
                // the node has both a left and right child
                node.rightChild?.min?.value?.let {
                    // replace the node’s value with
                    // the smallest value from the right subtree
                    node.value = it //TODO found a immutable way to do the same
                }
                // call remove on the right child to remove this swapped value
                node.rightChild = remove(node.rightChild, node.value)

            }
            value < node.value -> node.leftChild = remove(node.leftChild,value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        return node
    }

    private fun isBST(tree: BinaryNode<T>?, min: T?, max: T?): Boolean{
        tree ?: return true

        if(min != null && min >= tree.value) return false
        if(max != null && max < tree.value) return false

        return isBST(tree.leftChild,min,tree.value) &&
                isBST(tree.rightChild,tree.value, max)
    }

    override fun toString(): String  = root.toString()
}

fun main(args: Array<String>){

    val exampleTree = BinarySearchTree<Int>().apply {
        insert(3)
        insert(1)
        insert(4)
        insert(0)
        insert(2)
        insert(5)
    }
    "building a BST" example {
        println(exampleTree)
    }

    "finding a node" example {
        if (exampleTree.contains(5)) {
            println("Found 5!")
        } else {
            println("Couldn't find 5")
        }
    }

    "removing a node" example {
        println("Tree before removal:")
        println(exampleTree)
        exampleTree.remove(3)
        println("Tree after removing root:")
        println(exampleTree)
    }

}