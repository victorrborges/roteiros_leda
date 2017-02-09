package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	private int height(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node == null) {
			BSTNode<T> nodeNil = new BSTNode<T>();
			return nodeNil;
		} else if (node.isEmpty()) {
			return node;
		} else if (node.getData().equals(element)) {
			return node;
		} else if (element.compareTo(node.getData()) < 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) {
			return;
		}
		insert(this.getRoot(), element, new BSTNode<T>());
	}
	
	private void insert(BSTNode<T> node, T element, BSTNode<T> parent) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		}
		if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element, node);
		} else if (element.compareTo(node.getData()) > 0) {
			insert((BSTNode<T>) node.getRight(), element, node);
		}
		
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		if (node.isEmpty()) {
			return null;
		} else {
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			} else if (node.equals(node.getParent().getLeft())){
				return parent;
			} else {
				while(!parent.isEmpty() && node == parent.getRight()) {
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				if(!parent.isEmpty()) {
					return parent;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null) {
			return null;
		}
		BSTNode<T> node = this.search(element);
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		if (node.isEmpty()) {
			return null;
		} else {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());				
			} else if (node.getData().compareTo(node.getParent().getData()) > 0){
				return parent;
			} else {
				while(!parent.isEmpty() && node == parent.getLeft()) {
					node = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				if(!parent.isEmpty()) {
					return parent;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element == null) {
			return;
		}
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return;
		} else if (node.isLeaf()) {
			node.setData(null);
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(node.getLeft().getData());
			node.setLeft(node.getLeft().getLeft());
			node.setRight(node.getLeft().getRight());
			node.getLeft().setData(null);
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			node.setData(node.getRight().getData());
			node.setLeft(node.getRight().getLeft());
			node.setRight(node.getRight().getRight());
			node.getRight().setData(null);
		} else {
			BSTNode<T> minNode = minimum((BSTNode<T>) node.getRight());
			node.setData(minNode.getData());
			minNode.setData(null);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(array, 0, this.getRoot());
		return array;
	}

	private void preOrder(T[] array, int index, BSTNode<T> node) {
		if(!node.isEmpty()) {
			array[index] = (T) node.getData();
			index++;
			preOrder(array, index, (BSTNode<T>) node.getLeft());
			preOrder(array, index, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(array, 0, this.getRoot());
		return array;
	}

	private void order(T[] array, int index, BSTNode<T> node) {
		if(!node.isEmpty()) {
			order(array, index, (BSTNode<T>) node.getLeft());
			array[index] = (T) node.getData();
			index++;
			order(array, index, (BSTNode<T>) node.getRight());
		}
		
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, 0, this.getRoot());
		return array;
	}

	private void postOrder(T[] array, int index, BSTNode<T> node) {
		if(!node.isEmpty()) {
			postOrder(array, index, (BSTNode<T>) node.getLeft());
			array[index] = (T) node.getData();
			index++;
			postOrder(array, index, (BSTNode<T>) node.getRight());
		}		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
