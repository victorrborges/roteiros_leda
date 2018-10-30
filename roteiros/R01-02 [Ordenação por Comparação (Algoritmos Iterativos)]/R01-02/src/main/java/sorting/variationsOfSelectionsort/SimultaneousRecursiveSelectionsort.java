package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;

/**
 * Este algoritmo eh RECURSIVO e aplica o selectionsort na entrada selectionando o 
 * menor e o maior elemento a cada iteração e colocando eles nas posições corretas. 
 * Nas proximas iterações o espaço de trabalho do algoritmo deve excluir as posiçoes
 * dos elementos das iterações anteriores. 
 */
public class SimultaneousRecursiveSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex) {
			
			for (int j = leftIndex; j < rightIndex; j = j + 1) {
				if (array[j + 1].compareTo(array[j]) < 0) {
					util.Util.swap(array, j, j + 1);
				}
			}

			for (int j = rightIndex - 1; j > leftIndex; j = j - 1) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					util.Util.swap(array, j, j - 1);
				}
			}

			sort(array, leftIndex + 1, rightIndex - 1);

		}
	}

}
