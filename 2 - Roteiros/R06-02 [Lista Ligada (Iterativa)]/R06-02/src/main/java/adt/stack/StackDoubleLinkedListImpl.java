package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(this.isFull()) {
			throw new StackOverflowException();
		}
		
		if(element != null) {			
			this.top.insertFirst(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		
		DoubleLinkedListImpl<T> linkedList = (DoubleLinkedListImpl<T>) this.top;
		
		T element = linkedList.getHead().getData();
		
		this.top.removeFirst();
		
		return element;
	}

	@Override
	public T top() {
		if (this.isEmpty()) {
			return null;
		}
		
		DoubleLinkedListImpl<T> linkedList = (DoubleLinkedListImpl<T>) this.top;
		
		return linkedList.getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == this.top.size();
	}

}
