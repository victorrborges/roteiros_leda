package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if(this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.getNext().size();
		}
	}

	@Override
	public T search(T element) {
		if(this.isEmpty()) {
			return null;
		} else if (this.getData().equals(element)) {
			return element;
		} else {
			return this.getNext().search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<T>());
		} else {
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty()) {
		}
		if (this.getData().equals(element)) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
			} else {
				this.setData(this.getNext().getData()); 
				if (!this.getNext().getNext().isEmpty()) {
					this.setNext(this.getNext().getNext());
				} else {
					this.setNext(new RecursiveSingleLinkedListImpl<T>());
				}
			}
		} else {
			this.getNext().remove(element);
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		int i = -1;
		return this.toArray(array, i);
	}

	private T[] toArray(T[] array, int i) {
		if (this.isEmpty()) {
			return array;
		} else {
			i ++;
			array[i] = this.getData();
			return this.getNext().toArray(array, i);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
