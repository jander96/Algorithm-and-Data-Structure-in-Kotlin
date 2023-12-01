# Queues

We’re all familiar with waiting in line. Whether you’re in line to buy tickets to your
favorite movie or waiting for a printer to print a file, these real-life scenarios mimic
the queue data structure.
Queues use FIFO or first in, first out ordering, meaning the first element that was
added will always be the first one removed. Queues are handy when you need to
maintain the order of your elements to process later.

![](https://th.bing.com/th/id/R.d733a6c6e44093c70b6ab807a581a964?rik=fBcdGyyd832PUw&pid=ImgRaw&r=0)

The core operations for a queue are:
- **enqueue**: Inserts an element at the back of the queue and returns true if the
operation is successful.
- **dequeue**: Removes the element at the front of the queue and returns it.
- **isEmpty**: Checks if the queue is empty using the count property
- **peek**: Returns the element at the front of the queue without removing it.