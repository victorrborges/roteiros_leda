package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0 || array == null || leftIndex < 0 || rightIndex > array.length || leftIndex > rightIndex){
			return;
		}
		
		int maiorValor = array[leftIndex];
		int menorValor = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex ; i++) {
			if(array[i].compareTo(maiorValor) > 0){
				maiorValor = array[i];
			}
			if(array[i].compareTo(menorValor) < 0){
				menorValor = array[i];
			}
		}
		
		int[] freq = new int[maiorValor - menorValor + 1];
		for(int i = leftIndex; i <= rightIndex; i++) {
			freq[array[i] - menorValor] += 1;
		}
		
		for (int i = 1; i < freq.length; i++) {
			freq[i] += freq[i - 1];
		}
		
		Integer[] helper = new Integer[rightIndex + 1];
		
		for(int i = 0; i < helper.length; i ++){
			helper[i] = 0;
		}
		
		for (int i = rightIndex; i >= 0; i--) {
			helper[freq[array[i] - menorValor] - 1] = array[i];
			freq[array[i] - menorValor] -= 1;
		}
		
		for(int i = leftIndex; i <= rightIndex; i ++){
			array[i] = helper[i];
		}			
	}
}
