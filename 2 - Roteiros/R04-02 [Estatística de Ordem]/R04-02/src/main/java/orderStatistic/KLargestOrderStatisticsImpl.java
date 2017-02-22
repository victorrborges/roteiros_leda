package orderStatistic;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		
		// Como na especificacao do metodo abaixo (orderStatistics) foi dito que valores invalidos para k retornariam null,
		// tratei com exceptions apenas casos no qual o k for negativo, uma vez que causaria NegativeArraySizeException.
		
		if (k < 0) {
			throw new RuntimeException("K nao pode ser negativo");
		}
		
		T[] kLargests = (T[]) new Comparable[k];
		
		k = array.length - k + 1;
		
		for(int i = 0; i < kLargests.length; i ++){
			kLargests[i] = orderStatistics(array, k);
			k++;
		}
		
		return kLargests;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	public T orderStatistics(T[] array, int k){
		if (k < 1 || k > array.length){
			return null;
		}
		
		return quick(array, 0, array.length - 1, k - 1);
		
	}
	
	public T quick(T[] array, int leftIndex, int rightIndex, int k) {
		if (leftIndex == rightIndex) {
			return array[leftIndex];
		}
		
		int pivot = particiona(array, leftIndex, rightIndex);
			
		if (pivot == k){
			return array[k];
		}
		if (pivot > k) {
			return quick(array, leftIndex, pivot - 1, k);
		} else {
			return quick(array, pivot + 1, rightIndex, k);
		}	
	}
	
	public int particiona(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		
		for(int j = leftIndex + 1; j <= rightIndex; j = j + 1){
			if (array[j].compareTo(pivot) < 0) {
				i = i + 1;
				util.Util.swap(array, i, j);
			}
		}
		util.Util.swap(array, leftIndex, i);
		return i;
	}
	
}
