# Challenge 1 : Is it a BST?

Create a function that checks if a binary tree is a binary search tree.
### Solution:
A binary search tree is a tree where every left child is less than or equal to its parent,
and every right child is greater than its parent. An algorithm that verifies whether a
tree is a binary search tree involves going through all the nodes and checking for this
property.

```kotlin
     fun<T> isBST(tree: BinaryNode<T>?, min: T?, max: T?): Boolean{
        tree ?: return true

        if(min != null && min >= tree.value) return false
        if(max != null && max < tree.value) return false

        return isBST(tree.leftChild,min,tree.value) &&
            isBST(tree.rightChild,tree.value, max)
}
```

# Challenge 2 : Equality between BSTs
Override equals() to check whether two binary search trees are equal.