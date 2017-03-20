package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	public BSTNode<T> search(T element) {
		if (element == null || this.getRoot().isEmpty()) {
			return new BSTNode<T>();
		} else {
			BSTNode<T> node = super.search(this.getRoot(), element);
			if (node.isEmpty()) {
				splay((BSTNode<T>) node.getParent());
			} else {
				this.splay(node);
			}
			return node;
		}
	}

	public void insert(T element) {
		if (element != null) {
			super.insert(this.getRoot(), element);
			BSTNode<T> node = super.search(element);
			this.splay(node);
		}
	}

	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = super.search(element);
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			splay(parent);
			if (!node.isEmpty()) {
				super.remove(element);
			}
		}
	}

	private void splay(BSTNode<T> node) {
		if (node != null && node.getParent() != null) {

			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			BSTNode<T> grandParent = (BSTNode<T>) node.getParent().getParent();

			if (grandParent != null) {
				// Node no nivel 2+
				if (node.getParent().getLeft().equals(node)) {
					// Node esta na esquerda do seu pai
					if (node.getParent().getParent().getLeft().equals(node.getParent())) {
						// Pai do node tambem esta na esquerda do avo
						Util.rightRotation(grandParent);
						Util.rightRotation(parent);
					} else {
						// Pai do node esta na direita do avo
						Util.rightRotation(parent);
						Util.leftRotation(grandParent);
					}
				} else {
					// Node esta na direita do seu pai
					if (node.getParent().getParent().getLeft().equals(node.getParent())) {
						// Pai do node esta na esquerda do avo
						Util.leftRotation(parent);
						Util.rightRotation(grandParent);
					} else {
						// Pai do node tambem esta na direita do avo
						Util.leftRotation(grandParent);
						Util.leftRotation(parent);
					}
				}
			} else {
				// Node no nivel 1
				if (node.getParent().getLeft().equals(node)) {
					// Node esta a esquerda da raiz
					Util.rightRotation(parent);
				} else {
					// Node esta a direita da raiz
					Util.leftRotation(parent);
				}
			}
			if (node.getParent() == null) {
				this.root = node;
			}
			this.splay(node);
		}
	}
}
