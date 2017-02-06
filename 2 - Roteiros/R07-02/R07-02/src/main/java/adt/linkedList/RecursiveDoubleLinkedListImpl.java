package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> oldHead = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(), this.getPrevious());
		this.setData(element);
		this.setNext(oldHead);
		oldHead.setPrevious(this);		
	}

	@Override
	public void removeFirst() {
		this.setData(this.getNext().getData());
		this.setNext(this.getNext().getNext());
	}

	@Override
	public void removeLast() {
		T element = this.getLast(this);
		this.remove(element);
	}
	

	private T getLast(RecursiveSingleLinkedListImpl<T> node) {
		if (node.getNext().isEmpty()) {
			return node.getData();
		} else {
			return getLast(node.getNext());
		}
		
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
