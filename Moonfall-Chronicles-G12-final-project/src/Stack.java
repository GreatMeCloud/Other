/*
 * Stack.java
 * Implements a stack data structure for managing world map layers.
 * Supports push, pop, and peek operations to track nested map transitions.
 */

// Stack data structure implementation for managing world layers
public class Stack {
	String []data;
	int top;
	
	// Initialize stack with maximum capacity
	public Stack(int maxItems){
		data = new String[maxItems];
		top = -1;
	}
	 
	// Removes and returns the top element
	public String pop(){
		top--;
		return data[top + 1];
	}
	 
	// Adds element to the top of stack
	public void push(String item){
		if(top < data.length - 1) {
		    top++;
		    data[top] = item;
		}
	}
	 
	// Peeks at top element without removing it
	public String top(){
		return data[top];
	}
	 
	// Checks if stack is empty
	public boolean isEmpty(){
		if (top==-1) return true;
		else return false;
	}
	 
	// Returns current number of elements in stack
	public int size(){
		if (isEmpty()) return 0;
		else return top + 1;
	}
	 
	// Clears all elements from stack
	public void makeEmpty()
	{
		top = -1;
	}
}
