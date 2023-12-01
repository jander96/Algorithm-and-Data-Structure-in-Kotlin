# Stack

Stacks are everywhere. Some common examples of things you might stack:
- Pancakes.
- Books.
- Paper.
- Cash, especially cash. :]

The stack data structure is identical, in concept, to a physical stack of objects. When
you add an item to a stack, you place it on top of the stack. When you remove an
item from a stack, you always remove the top-most item.

![](https://th.bing.com/th/id/OIP.Oy5eeg1g55wHzZIWDFmjjgHaGV?rs=1&pid=ImgDetMain)

Stacks are useful, and also exceedingly simple. The main goal of building a stack is to
enforce how you access your data. If you had a tough time with the linked list
concepts, you’ll be glad to know that stacks are comparatively trivial.
There are only two essential operations for a stack:
• push: Adding an element to the top of the stack.
• pop: Removing the top element of the stack.
This means that you can only add or remove elements from one side of the data
structure.In computer science, a stack is known as the **LIFO** (last-in first-out) data
structure. Elements that are pushed in last are the first ones to be popped out.

Stacks are used prominently in all disciplines of programming, such as:
- Android uses the fragment stack to push and pop fragments into and out of an
Activity.
- Memory allocation uses stacks at the architectural level. Memory for local
variables is also managed using a stack.
- Search and conquer algorithms, such as finding a path out of a maze, use stacks to
facilitate backtracking.

### Less is more

You may have wondered if you could adopt the Kotlin collection interfaces for the
stack. A stack’s purpose is to limit the number of ways to access your data, and
adopting interfaces such as Iterable would go against this goal by exposing all of
the elements via iterators. In this case, less is more!