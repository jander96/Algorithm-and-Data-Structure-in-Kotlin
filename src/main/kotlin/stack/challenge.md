# Challenge 1: Reverse a LinkedList
Given a linked list, print the nodes in reverse order. You should not use recursion to
solve this problem.

### Solution 1

```kt
    fun <T> LinkedList<T>.printInReverse() {
        val stack = StackImpl<T>()
        for (node in this) {
            stack.push(node)
        }
        var node = stack.pop()
        while (node != null) {
            println(node)
            node = stack.pop()
        }
    }
```

# Challenge 2: The parentheses validation
Check for balanced parentheses. Given a string, check if there are ( and ) characters,
and return true if the parentheses in the string are balanced. For example:

```
// 1
h((e))llo(world)() // balanced parentheses
// 2
(hello world // unbalanced parentheses
```

### Solution 2
```kt
    fun String.checkParentheses(): Boolean {
        val stack = StackImpl<Char>()
        for (character in this) {
            when (character) {
                '(' -> stack.push(character)
                ')' -> if (stack.isEmpty) {
                            return false
                        } else {
                            stack.pop()
                        }
            }
        }
    return stack.isEmpty
    }
```