package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		if(this.isEmpty()) {
			return 0;
		} 
		
		SingleLinkedListNode<T> aux = this.getHead();
		int size = 1;
		while(!aux.getNext().isNIL()) {
			size += 1;
			aux = aux.getNext();
		}
		
		return size;		
	}

	@Override
	public T search(T element) {
		if (element == null) {
			return null;
		}
		
		SingleLinkedListNode<T> aux = this.getHead();
		while(!aux.getNext().isNIL()) {
			if (aux.getData().equals(element)) {
				return element;
			}
			aux = aux.getNext();
		}
		
		return null;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
			
			if(this.isEmpty()) {
				
				this.setHead(newNode);
			
			} else {
				
				SingleLinkedListNode<T> aux = this.getHead();
				
				while(!aux.getNext().isNIL()) {
					aux = aux.getNext();
				}
				
				aux.setNext(newNode);
				
			}
		}
		
	}

	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) {
			return;
		}
		
		int i = this.size();
		while(i > 0) {
			SingleLinkedListNode<T> auxPrev = null;
			SingleLinkedListNode<T> aux = this.getHead();
			for (int j = 0; j < i; j++) {
				auxPrev = aux;
				aux = aux.getNext();
			}
			if (!aux.isNIL() && aux.getData().equals(element)) {
				auxPrev.setNext(aux.getNext());
				return;
			}
			i--;
		}
		
//		if (this.getHead().getData().equals(element)) {
//			this.setHead(this.getHead().getNext());
//		}
//		
//		SingleLinkedListNode<T> aux = this.getHead();		
//		SingleLinkedListNode<T> auxPrev = null;
//		while(!aux.isNIL() && !aux.getData().equals(element)) {
//			auxPrev = aux;
//			aux = aux.getNext();
//		}
//		
//		if (aux.isNIL()) {
//			return;
//		} else {
//			auxPrev.setNext(aux.getNext());	
//		}	
		
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array =  (T[]) new Comparable[this.size()];
		
		SingleLinkedListNode<T> aux = this.getHead();
		int i = 0;
		while(!aux.isNIL()) {
			array[i] = aux.getData();
			i ++;
			aux = aux.getNext();
		}		
		
		return array;
	}
	
	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}


}