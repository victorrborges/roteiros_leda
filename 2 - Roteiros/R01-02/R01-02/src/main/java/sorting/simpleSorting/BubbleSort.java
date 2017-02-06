package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i = i + 1){
			for (int j = 0; j < array.length - i - 1; j = j + 1){
				if (array[j].compareTo(array[j + 1]) > 0){
					util.Util.swap(array, j, j + 1);
				}
			}
		}
		
	}
}
