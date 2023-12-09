# Challenge: Serialization of a Binary Tree
A common task in software development is serializing an object into another data
type. This process is known as serialization, and it allows custom types to be used in
systems that only support a closed set of data types.

An example of serialization is JSON. Your task is to devise a way to serialize a binary
tree into a list, and a way to deserialize the list back into the same binary tree.

  
### Solution:
```kotlin
    fun traversePreOrderWithNull(visit: Visitor<T>) {
        visit(value)
        leftChild?.traversePreOrderWithNull(visit) ?: visit(null)
        rightChild?.traversePreOrderWithNull(visit) ?: visit(null)
    }
```
#### Serialization
```kotlin
    fun serialize(node: BinaryNode<T> = this): MutableList<T?> {
        val list = mutableListOf<T?>()
        node.traversePreOrderWithNull { list.add(it) }
        return list
    }
```

#### Deserialization
```kotlin
    fun deserialize(list: MutableList<T?>): BinaryNode<T?>? {

        val rootValue = list.removeFirstOrNull(list.size - 1) ?: return null

        val root = BinaryNode<T?>(rootValue)
        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)
        return root
    }
```

Here’s how the code works:
1. This is the base case. If removeAt returns null, there are no more elements in
   the array, thus you’ll end recursion here.
2. You reassemble the tree by creating a node from the current value and
   recursively calling deserialize to assign nodes to the left and right children.
   Notice this is very similar to the pre-order traversal, except, in this case, you’re
   building nodes rather than extracting their values.