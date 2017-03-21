package adt.rbtree;

import java.util.ArrayList;
import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		if (root != null && !root.isEmpty()) {
			return blackHeight((RBNode<T>) this.root);
		}
		return 0;
	}

	public int blackHeight(RBNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (node.getColour() == Colour.BLACK) {
				return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			} else {
				return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			}
		} else {
			return 0;
		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		if (root != null && !root.isEmpty()) {
			return verifyChildrenOfRedNodes((RBNode<T>) root.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) root.getRight());
		}
		return true;
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				if (((RBNode<T>) node.getLeft()).getColour() != Colour.BLACK || ((RBNode<T>) node.getRight()).getColour() != Colour.BLACK) {
					return false;
				}
			}
			return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (root != null) {
			int leftBlackHeight = blackHeight((RBNode<T>) root.getLeft());
			int rightBlackHeight = blackHeight((RBNode<T>) root.getRight());
			if (rightBlackHeight == leftBlackHeight) {
				return true;
			} else {
				throw new RuntimeException("Black Heights are different");
			}
		}

		return true;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			RBNode<T> NIL = new RBNode<>();
			this.insert(value, (RBNode<T>) this.root, NIL);
		}
	}

	private void insert(T element, RBNode<T> node, RBNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			if (!node.equals(root)) {
				node.setParent(parent);
			}
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if ((node.getData().compareTo(element)) < 0) {
				insert(element, (RBNode<T>) node.getRight(), node);
			} else {
				insert(element, (RBNode<T>) node.getLeft(), node);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> array = new ArrayList<>();
		rbPreOrder((RBNode<T>) this.root, array);
		return array.toArray(new RBNode[array.size()]);
	}

	private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> array) {
		if (!node.isEmpty()) {
			array.add(node);
			rbPreOrder((RBNode<T>) node.getLeft(), array);
			rbPreOrder((RBNode<T>) node.getRight(), array);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(this.getRoot())) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (parent.getColour() == Colour.BLACK) {
			return;
		} else {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) node.getParent().getParent();
		RBNode<T> uncle;
		
		if (parent.equals(grandParent.getLeft())) {
			uncle = (RBNode<T>) grandParent.getRight();
		} else {
			uncle = (RBNode<T>) grandParent.getLeft();
		}

		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			fixUpCase1(grandParent);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		if (node.getParent().getRight().equals(node) && node.getParent().getParent().getLeft().equals(node.getParent())) {
			Util.leftRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getLeft();
		} else if (node.getParent().getLeft().equals(node) && node.getParent().getParent().getRight().equals(node.getParent())) {
			Util.rightRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) node.getParent().getParent();
		
		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED); 

		if (node.equals(parent.getLeft())) {
			Util.rightRotation(grandParent);
		} else {
			Util.leftRotation(grandParent);
		}
	}
}
