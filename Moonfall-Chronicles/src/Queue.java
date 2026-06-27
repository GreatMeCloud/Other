
/*
 * Queue.java
 * Implements a circular queue data structure for managing combat turn sequences.
 * Supports enqueue, dequeue, and peeking operations with a fixed maximum capacity.
 */

// Circular queue data structure implementation
public class Queue {
	String[] data;
    int front;
    int rear;
    int size;

    // Initialize circular queue with maximum capacity
    public Queue(int maxItems) {
        data = new String[maxItems];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Adds element to the rear of queue
    public void enqueue(String item) {

        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }

        // move rear circularly
        rear = (rear + 1) % data.length;

        data[rear] = item;

        size++;
    }

    // Removes and returns element from front of queue
    public String dequeue() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        
        String value = data[front];

        // move front circularly
        front = (front + 1) % data.length;

        size--;

        return value;
    }

    // Peeks at front element without removing it
    public String front() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        return data[front];
    }

    // Checks if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks if queue has reached maximum capacity
    public boolean isFull() {
        return size == data.length;
    }

    // Returns current number of elements in queue
    public int size() {
        return size;
    }

    // Clears all elements from queue
    public void makeEmpty() {
        front = 0;
        rear = -1;
        size = 0;
    }
}
