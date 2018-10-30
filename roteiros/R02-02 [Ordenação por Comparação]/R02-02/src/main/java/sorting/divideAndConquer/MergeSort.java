package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int midIndex = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, midIndex);
			sort(array, midIndex + 1, rightIndex);

			merge(array, leftIndex, midIndex, rightIndex);
		}
	}

	public void merge(T[] array, int leftIndex, int midIndex, int rightIndex) {
		@SuppressWarnings("unchecked")
		T[] helper = (T[]) new Comparable[rightIndex + 1];

		for (int i = leftIndex; i <= rightIndex; i = i + 1) {
			helper[i] = array[i];
		}

		int i = leftIndex;
		int j = midIndex + 1;
		int k = leftIndex;

		while (i <= midIndex && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i = i + 1;
			} else {
				array[k] = helper[j];
				j = j + 1;
			}
			k = k + 1;
		}

		while (i <= midIndex) {
			array[k] = helper[i];
			i = i + 1;
			k = k + 1;
		}

		while (j <= rightIndex) {
			array[k] = helper[j];
			j = j + 1;
			k = k + 1;
		}
	}
}
