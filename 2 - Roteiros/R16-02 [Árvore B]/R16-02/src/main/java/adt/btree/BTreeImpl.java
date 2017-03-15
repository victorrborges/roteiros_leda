package adt.btree;

import java.util.ArrayList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node.isEmpty()) {
			if (node.equals(this.getRoot())) {
				return -1;
			}
			return 0;
		} else {
			if (node.getChildren().size() > 0) {
				return height(node.getChildren().getFirst()) + 1;
			} else {
				return 0;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public BNode<T>[] depthLeftOrder() {
		ArrayList<BNode<T>> array = new ArrayList<>();
		if (!this.isEmpty()) {
			depthLeftOrder(getRoot(), array);
		} 
		return array.toArray(new BNode[array.size()]);
	}

	private void depthLeftOrder(BNode<T> node, List<BNode<T>> array) {
		if (node.isEmpty())
			return;
		else {
			array.add(node);
			for (BNode<T> child : node.getChildren()) {
				depthLeftOrder(child, array);
			}
		}
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return size(getRoot());
		}
	}

	private int size(BNode<T> node) {
		if (isEmpty()) {
			return 0;
		} else {
			int elements = node.size();
			for (BNode<T> child : node.getChildren()) {
				elements += size(child);
			}
			return elements;
		}
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element == null) {
			return new BNodePosition<>();
		}
		return search(getRoot(), element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		int index = 0;
		// Chega ate o indice onde o elemento pode estar
		while (index < node.size() && element.compareTo(node.getElementAt(index)) > 0) {
			index++;
		}
		// Se o Node for o buscado, retorne ele
		if (index < node.size() && element.equals(node.getElementAt(index))) {
			return new BNodePosition<>(node, index);
			// Se nao, cheque se o node eh folha, se sim, o elemento nao esta na
			// arvore
		} else if (node.isLeaf()) {
			return new BNodePosition<>();
			// Chamada recursiva para os filhos do node
		} else {
			return search(node.getChildren().get(index), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(getRoot(), element);
		}
	}

	private void insert(BNode<T> node, T element) {
		int index = 0;
		while (index < node.size() && node.getElementAt(index).compareTo(element) < 0) {
			index++;
		}
		if (index >= node.size() || !node.getElementAt(index).equals(element)) {
			if (node.isLeaf()) {
				node.addElement(element);
			} else {
				insert(node.getChildren().get(index), element);
			}
			// Adicionei num node folha e, se preciso, terei que fazer split
			// Adicao Bottom-Up
			if (node.size() > node.getMaxKeys())
				split(node);
		}
	}

	private void split(BNode<T> node) {
		// Escolha o elemento medio
		int midIndex = (node.getElements().size() / 2);
		BNode<T> left = new BNode<>(this.order);
		BNode<T> right = new BNode<>(this.order);
		// Valores menores que a mediana sao colocados em um novo node folha
		// (esquerdo)

		// Valores maiores sao colocados em uma outro novo node folha (direito)
		for (int i = 0; i < node.size(); i++) {
			if (i < midIndex)
				left.getElements().add(node.getElementAt(i));
			else if (i > midIndex)
				right.getElements().add(node.getElementAt(i));
		}
		T midElement = node.getElementAt(midIndex);
		if (!node.isLeaf()) {
			if (node.getChildren().size() > 0) {
				for (int i = 0; i <= midIndex; i++) {
					left.addChild(i, node.getChildren().get(i));
				}
				int index = 0;
				for (int i = midIndex + 1; i < node.getChildren().size(); i++) {
					right.addChild(index, node.getChildren().get(i));
					index += 1;
				}
			}
		}
		// Um split na raiz origina uma nova raiz
		if (node.equals(this.getRoot())) {
			BNode<T> newRoot = new BNode<>(this.order);
			newRoot.addElement(midElement);
			node.setParent(newRoot);
			this.root = newRoot;
			newRoot.addChild(0, left);
			newRoot.addChild(1, right);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);
		} else {
			node.addChild(0, left);
			node.addChild(1, right);
			promote(node);
		}
	}

	private void promote(BNode<T> node) {
		int midIndex = (node.getElements().size() / 2);
		T midElement = node.getElementAt(midIndex);
		node.getElements().clear();
		node.addElement(midElement);
		BNode<T> parent = node.getParent();
		if (parent != null) {
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			int index = parent.getChildren().indexOf(node);
			parent.addElement(midElement);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index + 1, node.getChildren().get(1));
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			parent.getChildren().remove(node);
		}
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
