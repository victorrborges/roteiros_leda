package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	private int height(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight()));
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node == null) {
			return;
		}
		int balance = calculateBalance(node);

		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> right = (BSTNode<T>) node.getRight();

		if (Math.abs(balance) <= 1)
			return;

		if (balance < 0) {
			if (calculateBalance(right) <= 0) {
				Util.leftRotation(node);
			} else {
				Util.rightRotation(right);
				Util.leftRotation(node);
			}
		} else {
			if (calculateBalance(left) >= 0) {
				Util.rightRotation(node);
			} else {
				Util.leftRotation(left);
				Util.rightRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(array, 0, this.getRoot());
		return array;
	}

	private int preOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[index] = (T) node.getData();
			index++;
			index = preOrder(array, index, (BSTNode<T>) node.getLeft());
			index = preOrder(array, index, (BSTNode<T>) node.getRight());
		}
		return index;
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		order(array, 0, this.getRoot());
		return array;
	}

	private int order(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = order(array, index, (BSTNode<T>) node.getLeft());
			array[index] = (T) node.getData();
			index++;
			index = order(array, index, (BSTNode<T>) node.getRight());
		}
		return index;
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, 0, this.getRoot());
		return array;
	}

	private int postOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = postOrder(array, index, (BSTNode<T>) node.getLeft());
			index = postOrder(array, index, (BSTNode<T>) node.getRight());
			array[index] = (T) node.getData();
			index++;
		}
		return index;
	}
}
