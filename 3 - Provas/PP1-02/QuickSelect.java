package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
* que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
* dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
* o quickselect eh executado (recursivamente) apenas na metade que contem o
* elemento que ele procura (o k-esimo menor elemento). Isso reduz a
* complexidade de O(n.log n) para O(n)
* 
* @author Adalberto
*
*/
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if (array == null || k > array.length || k < 1) {
			return null;
		} 
		
		return quick(array, 0, array.length - 1, k - 1);		
		
	}
	
	public T quick(T[] array, int ini, int fim, int k) {
		
		if(ini <= fim) {
				
			int pos_piv = particiona(array, ini, fim);
			
			if(pos_piv == k) {
				return array[pos_piv];
			}
			
			if(k > pos_piv) {
				
				return quick(array, pos_piv + 1, fim, k);
			}
				
			if(k < pos_piv) {
			
				return quick(array, ini, pos_piv - 1, k);
			
			}
		}
		return null;
	}
	
	
	public int particiona(T[] array, int ini, int fim) {
		T piv = array[ini];
		int i = ini;
		for (int j = ini + 1; j <= fim; j ++) {
			if (array[j].compareTo(piv) < 0) {
				swap(array, i, j);
			}
		}
		swap(array, i, ini);
		return i;
	}

	private void swap(T[] array, int i, int j) {
		T aux = array[i];
		array[i] = array[j];
		array[j] = aux;		
	}
	
}