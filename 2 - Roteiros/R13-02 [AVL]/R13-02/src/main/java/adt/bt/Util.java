package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		
		BTNode<T> parent = node.getParent();
		BTNode<T> right = node.getRight();

		if (node.equals(parent.getLeft())) {
			parent.setLeft(right);
		} else {
			parent.setRight(right);
		}

		right.setParent(parent);
		node.setParent(right);
		node.setRight(right.getLeft());
		node.getRight().setParent(node);
		right.setLeft(node);

		return (BSTNode<T>) right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		
		BTNode<T> parent = node.getParent();
		BTNode<T> left = node.getLeft();

		if (node.equals(parent.getLeft())) {
			parent.setLeft(left);
		} else {
			parent.setRight(left);
		}

		left.setParent(parent);
		node.setParent(left);
		node.setLeft(left.getRight());
		node.getLeft().setParent(node);
		left.setRight(node);

		return (BSTNode<T>) left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
