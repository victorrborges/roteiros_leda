package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		T[] elementos = this.toArray();
		for (int i = 0; i < elementos.length - 1; i++) {
			for (int j = i + 1; j < elementos.length; j++) {
				if (elementos[j].equals(elementos[i])) {
					this.remove(elementos[j]);
				}
			}
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedList<T> newSet = new SetLinkedListImpl<T>();
		SingleLinkedListNode<T> auxNode = this.getHead();
		while(auxNode != null) {
			newSet.insert(auxNode.getData());
			auxNode = auxNode.getNext();
		}
		
		T[] elementos = otherSet.toArray();
		for (int i = 0; i < elementos.length; i++) {
			newSet.insert(elementos[i]);
		}
		
		return newSet;
		
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedList<T> newSet = new SetLinkedListImpl<T>();
		T[] array1 = this.toArray();
		T[] array2 = otherSet.toArray();
		for (int i = 0; i < array1.length; i++) {
			boolean insira = false;
			for (int j = 0; j < array2.length; j++) {
				if (array2[j].equals(array1[i])) {
					insira = true;
				}
			}
			if (insira) {
				newSet.insert(array1[i]);
			}
		}
		return newSet;
//		SetLinkedList<T> newSet = new SetLinkedListImpl<T>();
//		T[] array = this.toArray();
//		for (int i = 0; i < array.length; i++) {
//			if (otherSet.search(array[i]) != null) {
//				newSet.insert(array[i]);
//			}
//		}
//		return newSet;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		T[] elementos = otherSet.toArray();
		for (int i = 0; i < elementos.length; i++) {
			this.insert(elementos[i]);
		}
	}
}