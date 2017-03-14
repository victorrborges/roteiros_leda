package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {

			int midIndex = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, midIndex);
			sort(array, midIndex + 1, rightIndex);

			if (rightIndex + leftIndex <= SIZE_LIMIT) {
				insertionSort(array, leftIndex, rightIndex);
				INSERTIONSORT_APPLICATIONS = INSERTIONSORT_APPLICATIONS + 1;
			} else {
				mergeSort(array, leftIndex, midIndex, rightIndex);
				MERGESORT_APPLICATIONS = MERGESORT_APPLICATIONS + 1;
			}

		}
	}

	public void mergeSort(T[] array, int leftIndex, int midIndex, int rightIndex) {
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

	public void insertionSort(T[] array, int leftIndex, int rightIndex) {

		for (int i = leftIndex + 1; i <= rightIndex; i = i + 1) {
			int j = i;

			while (j > 0 && array[j - 1].compareTo(array[j]) > 0) {

				util.Util.swap(array, j - 1, j);
				j = j - 1;

			}
		}

	}
}
