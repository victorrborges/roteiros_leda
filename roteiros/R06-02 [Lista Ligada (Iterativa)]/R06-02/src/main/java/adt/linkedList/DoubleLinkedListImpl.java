package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
		if(this.isEmpty()) {
			this.setHead(newNode);
		} else {
			newNode.setNext(this.getHead());
			this.setHead(newNode);
		}
	}

	@Override
	public void removeFirst() {
		if (this.isEmpty()) {
			return;
		} else {
			this.setHead(this.getHead().getNext());
		}
		
	}

	@Override
	public void removeLast() {
		if (this.isEmpty()) {
			return;
		}
		
		if (this.getHead().getNext().isNIL()) {
			
			this.setHead(new DoubleLinkedListNode<T>());
	
		} else {
			
			SingleLinkedListNode<T> aux = this.getHead();	
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			aux.setData(null);
		}
		
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
