package adt.bst.extended;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum(){
		return maximumRecursive(this.getRoot());
	}
	
	private BSTNode<T> maximumRecursive(BSTNode<T> node){
		BSTNode<T> result = (BSTNode<T>) node.getParent();
		if(!node.isEmpty()){
			result = maximumRecursive((BSTNode<T>) node.getRight());
		}
		return result;
	}

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum(){
		return minimumRecursive(this.getRoot());
	}
	
	private BSTNode<T> minimumRecursive(BSTNode<T> node){
		BSTNode<T> result = (BSTNode<T>) node.getParent();
		if(!node.isEmpty()){
			result = minimumRecursive((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario
	 * o sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);

		if (node == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			} else {
				return sucessorRecursive(node, (BSTNode<T>) node.getParent());
			}
		}
	}
	
	private BSTNode<T> sucessorRecursive(BSTNode<T> node, BSTNode<T> parent) {
		if (parent != null && !parent.isEmpty() && node == parent.getRight()) {
			return sucessorRecursive(parent, (BSTNode<T>) parent.getParent());
		}
		
		if(parent != null && !parent.isEmpty()) {
			return parent;
		} else {
			return null;
		}
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
	 * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
	 * o predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);

		if (node == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else {
				return predecessorRecursive(node, (BSTNode<T>) node.getParent());
			}
		}
	}

	private BSTNode<T> predecessorRecursive(BSTNode<T> node, BSTNode<T> parent) {
		if (parent != null && !parent.isEmpty() && node == parent.getLeft()) {
			return predecessorRecursive(parent, (BSTNode<T>) parent.getParent());
		}
		
		if(parent != null && !parent.isEmpty()) {
			return parent;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] elementsAtDistance(int k) {
		ArrayList<T> result = new ArrayList<T>();
		if (this.getRoot() != null && k >= 0) {
			if (k == 0) {
				result.add(this.getRoot().getData());
			} else {
				elementsAtDistanceRecursive(k - 1, result, this.getRoot(), 0);
			}
		}
		return (T[]) result.toArray(new Comparable[0]);
	}

	private void elementsAtDistanceRecursive(int k, ArrayList<T> result, BSTNode<T> node, int index) {
		
		if (index > k) {
			return;
		}
		if (node != null) {
			if (index == k && !node.isEmpty()) {
				if (!node.getLeft().isEmpty()) {
					result.add(node.getLeft().getData());
				}
				if (!node.getRight().isEmpty()) {
					result.add(node.getRight().getData());
				}
			}
			if (index < k) {
				index++;
				elementsAtDistanceRecursive(k, result, (BSTNode<T>) node.getLeft(), index);
				elementsAtDistanceRecursive(k, result, (BSTNode<T>) node.getRight(), index);
			}
		}		
	}
}