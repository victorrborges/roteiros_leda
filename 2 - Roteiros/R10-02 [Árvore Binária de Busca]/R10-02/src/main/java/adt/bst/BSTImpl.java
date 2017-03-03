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

	protected int height(BSTNode<T> node) {
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
		if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (element.compareTo(node.getData()) > 0) {
			insert((BSTNode<T>) node.getRight(), element);
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

		if (node == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			} else {
				while (parent != null && !parent.isEmpty() && node == parent.getRight()) {
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

	@Override
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

	@Override
	public void remove(T element) {
		if (element == null) {
			return;
		}
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return;
		} else if (node.getParent() == null) {
			removeRoot(node);
			return;
		} else if (node.isLeaf()) {
			removeLeaf(node);
			return;
		} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			removeTwoDegree(node);
			return;
		} else {
			removeOneDegree(node);
			return;
		}
	}

	private void removeRoot(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			this.root = (BSTNode<T>) node.getLeft();
			this.root.setParent(null);
			node = null;
			return;
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			this.root = (BSTNode<T>) node.getRight();
			this.root.setParent(null);
			node = null;
			return;
		} else {
			removeTwoDegree(node);
			return;
		}
	}

	private void removeLeaf(BSTNode<T> node) {
		node.setData(null);
	}

	private void removeOneDegree(BSTNode<T> node) {
		if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.getLeft().setParent(node.getParent());
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getLeft());
			} else {
				node.getParent().setRight(node.getLeft());
			}
			return;
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			node.getRight().setParent(node.getParent());
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getRight());
			} else {
				node.getParent().setRight(node.getRight());
			}
			return;
		}
	}

	private void removeTwoDegree(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T data = sucessor.getData();
		remove(sucessor.getData());
		node.setData(data);
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
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
