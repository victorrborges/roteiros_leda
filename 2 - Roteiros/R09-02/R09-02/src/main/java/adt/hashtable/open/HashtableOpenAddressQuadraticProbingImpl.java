package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) {
			throw new HashtableOverflowException();
		}
		if (element != null) {
			if (this.indexOf(element) == -1) {
				HashFunctionQuadraticProbing<T> hashFunction = (HashFunctionQuadraticProbing<T>) this.getHashFunction();
				
				int probe = 0;
				
				while (probe < this.capacity()) {
					int indice = hashFunction.hash(element, probe);
					
					if (this.table[indice] == null || this.table[indice].equals(deletedElement)) {
						this.table[indice] = element;
						this.elements++;
						return;
					} else {
						super.COLLISIONS++;
						probe++;
					}
					
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int indice = this.indexOf(element);
			if (indice != -1) {
				this.table[indice] = deletedElement;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		if (this.indexOf(element) != -1) {
			return element;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int probe = 0;
			HashFunctionQuadraticProbing<T> hashFunction = (HashFunctionQuadraticProbing<T>) this.getHashFunction();
			
			while (probe < this.capacity()) {
				int indice = hashFunction.hash(element, probe);
				if (this.table[indice] != null && this.table[indice].equals(element)) {
						return indice;
				} else {
					probe++;
				}
			}
		}
		return -1;
	}
}
