package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		// Pesquisa o local
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (x.getForward(i).getKey() < key) {
				x = x.getForward(i);
			}
			// Guarda o caminho
			update[i] = x;
		}
		x = x.getForward(0);
		if (x.getKey() == key) {
			x.setValue(newValue);
		} else {
			x = new SkipListNode<T>(key, height, newValue);
			// Altera os ponteiros
			for (int i = 0; i < height; i++) {
				x.forward[i] = update[i].getForward(i);
				update[i].forward[i] = x;
			}
		}
	}

	@Override
	public void remove(int key) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		// Pesquisa o local
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (x.getForward(i).getKey() < key) {
				x = x.getForward(i);
			}
			// Guarda o caminho
			update[i] = x;
		}
		x = x.getForward(0);
		if (x.getKey() == key) {
			// Altera os ponteiros
			for (int i = 0; i < this.maxHeight; i++) {
				if (update[i].getForward(i) != x) {
					break;
				}
				update[i].forward[i] = x.getForward(i);
			}
		}
	}

	@Override
	public int height() {
		for (int i = this.maxHeight - 1; i > 0; i--) {
			// Quando achar um no que nao seja o sentinela, retorna i
			if (this.root.getForward(i).getValue() != NIL.getValue()) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			// Anda ate o ultimo no com mesma altura
			while (x.getForward(i).getKey() < key) {
				x = x.getForward(i);
			}
		}
		x = x.getForward(0);
		if (x.getKey() == key) {
			return x;
		} else {
			// Chave nao existe
			return null;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> x = this.root.getForward(0);
		// Anda no nivel mais baixo ate encontrar o no sentinela
		while (!x.equals(NIL)) {
			x = x.getForward(0);
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		@SuppressWarnings("unchecked")
		// +2 pois deve adicionar os nos sentinelas
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		SkipListNode<T> x = this.root;
		int index = 0;
		// A condicao ira quebrar depois do sentinela
		while (x != null) {
			array[index++] = x;
			x = x.getForward(0);
		}
		return array;
	}

}
