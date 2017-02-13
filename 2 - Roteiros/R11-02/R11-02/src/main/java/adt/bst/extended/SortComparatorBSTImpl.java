package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		boolean trocou;
		
		for (int i = 0; i < array.length; i++) {
			trocou = false;
			for (int j = 0; i < array.length - j - 1; j++) {
				if (comparator.compare(array[j], array[j + 1]) > 0) {
					util.Util.swap(array, j, j + 1);
					trocou = true;
				}
			}
			if (!trocou) {
				return array;
			}
		}
		
		return array;
		
	}

	@Override
	public T[] reverseOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(array, 0, this.getRoot());
		return array;
	}

	private int reverseOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = reverseOrder(array, index, (BSTNode<T>) node.getRight());
			array[index] = (T) node.getData();
			index++;
			index = reverseOrder(array, index, (BSTNode<T>) node.getLeft());
		}
		return index;
		
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node == null) {
			BSTNode<T> nodeNil = new BSTNode<T>();
			return nodeNil;
		} else if (node.isEmpty()) {
			return node;
		} else if (comparator.compare(node.getData(), element) == 0) {
			return node;
		} else if (comparator.compare(node.getData(), element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}
	
	public void insert(T element) {
		if (element == null) {
			return;
		}
		if (isEmpty()) {
			this.root.setLeft(new BSTNode<T>());
			this.root.setRight(new BSTNode<T>());
			this.root.getLeft().setParent(this.root);
			this.root.getRight().setParent(this.root);
			this.root.setData(element);
		}

		insert(this.getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {

		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			return;
		}
		if (comparator.compare(element, node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (comparator.compare(element, node.getData()) > 0) {
			insert((BSTNode<T>) node.getRight(), element);
		}

	}
	
	public BSTNode<T> predecessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		if (node == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else if (comparator.compare(node.getData(), node.getParent().getData()) > 0) {
				return parent;
			} else {
				while (parent != null && parent != null && node == parent.getLeft()) {
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				if (parent != null && !parent.isEmpty()) {
					return parent;
				} else {
					return null;
				}
			}
		}
	}
}
