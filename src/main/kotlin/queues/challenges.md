# Challenge Monopoly

Imagine you’re playing a game of Monopoly with your friends. The problem is that
everyone always forgets whose turn it is! Create a Monopoly organizer that tells you
whose turn it is. A great option is to create an extension function for Queue that
always returns the next player.

### Solution: 
```kotlin
    fun <T> Queue<T>.nextPlayer(): T? {

        val person = this.dequeue() ?: return null

        this.enqueue(person)

        return person
    }
```

# Challenge Reverse data
Implement a method to reverse the contents of a queue using an extension function

### Solution:
```kotlin
    fun <T> Queue<T>.reverse() {

        val aux = StackImpl<T>()

        var next = this.dequeue()
        while (next != null) {
            aux.push(next)
            next = this.dequeue()
        }

        next = aux.pop()
        while (next != null) {
            this.enqueue(next)
            next = aux.pop()
        }
    }
```

