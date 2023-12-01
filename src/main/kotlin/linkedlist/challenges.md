# Challenge 1: Reverse a linked list
Create an extension function that prints out the elements of a linked list in reverse
order. Given a linked list, print the nodes in reverse order. For example:
```
1 -> 2 -> 3
// should print out the following:
3
2
1
```
### Solution 1

```kt
fun <T> LinkedList<T>.printInReverse() {
    this.nodeAt(0)?.printInReverse()
}
```

```kt
fun <T> Node<T>.printInReverse() {
    this.next?.printInReverse()
}
```

```kt
fun <T> Node<T>.printInReverse() {
    this.next?.printInReverse()
    if (this.next != null) {
        print(" -> ")
    }

    print(this.value.toString())
}
```

# Challenge 2: The item in the middle

Given a linked list, find the middle node of the list. For example:
```
    1 -> 2 -> 3 -> 4
    // middle is 3
    1 -> 2 -> 3
    // middle is 2
```

### Solution 2

```kt
    fun <T> LinkedList<T>.getMiddle(): Node<T>? {
        var slow = this.nodeAt(0)
        var fast = this.nodeAt(0)
        
        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                fast = fast.next
                slow = slow?.next
            }
        }
        return slow
    }
```