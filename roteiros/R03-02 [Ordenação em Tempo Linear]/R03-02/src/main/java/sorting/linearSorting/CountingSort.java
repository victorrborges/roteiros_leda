package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (array.length == 0 || array == null || leftIndex < 0 || rightIndex > array.length || leftIndex > rightIndex){
			return;
		}
		
		int maiorValor = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex ; i++) {
			if(array[i].compareTo(maiorValor) > 0){
				maiorValor = array[i];
			}
		}
		
		int[] freq = new int[maiorValor + 1];
		for(int i = leftIndex; i <= rightIndex; i++) {
			freq[array[i]] += 1;
		}
		
		for (int i = 1; i < freq.length; i++) {
			freq[i] += freq[i - 1];
		}
		
		Integer[] helper = new Integer[rightIndex + 1];
		
		for(int i = 0; i < helper.length; i ++){
			helper[i] = 0;
		}
		
		for (int i = rightIndex; i >= 0; i--) {
			helper[freq[array[i]] - 1] = array[i];
			freq[array[i]] -= 1;
		}
		
		for(int i = leftIndex; i <= rightIndex; i ++){
			array[i] = helper[i];
		}		
		
	}

}
