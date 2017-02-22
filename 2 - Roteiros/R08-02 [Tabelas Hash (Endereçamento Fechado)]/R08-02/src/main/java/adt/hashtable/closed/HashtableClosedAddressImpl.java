package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		while(!util.Util.isPrime(++number));
		return number;
	}

	@Override
	public void insert(T element) {
		if (indexOf(element) != -1) {
			
		}
		HashFunctionClosedAddress<T> hashFunction = (HashFunctionClosedAddress<T>) this.getHashFunction();
		int hashCode = hashFunction.hash(element);
		if (this.table[hashCode] == null) {
			LinkedList<T> ll = new LinkedList<T>();
			ll.add(element);
			this.table[hashCode] = ll;
		} else {
			LinkedList<T> ll = (LinkedList<T>) this.table[hashCode];
			ll.add(element);
			this.COLLISIONS++;
		}
		this.elements++;
	}

	@Override
	public void remove(T element) {
		int index = indexOf(element);
		LinkedList<T> ll = (LinkedList<T>) this.table[index];
		ll.remove(element);
		if (!ll.isEmpty()) {
			this.COLLISIONS--;
		}
		this.elements--;
	}

	@Override
	public T search(T element) {
		if (indexOf(element) != -1) {
			return element;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		for (int i = 0; i < this.table.length; i++) {
			if (this.table[i] != null) {
				LinkedList<T> ll = (LinkedList<T>) this.table[i];
				if (ll.contains(element)) {
					return i;
				}
			}
		}
		return -1;
	}
}
