package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 * @param <T>
 *
 */

public class Vetor<T extends Comparable<T>> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<T> comparadorMaximo;
	private Comparator<T> comparadorMinimo;

	@SuppressWarnings("unchecked")
	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = (T[]) new Comparable[tamanho];
	}

	public void setComparadorMaximo(Comparator<T> comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator<T> comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		this.indice = this.indice + 1;
		this.arrayInterno[indice] = o;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T t = null;
		for (int i = 0; i < arrayInterno.length; i = i + 1){
			if (arrayInterno[i] != null){
				if (arrayInterno[i].equals(o)){
					t = arrayInterno[i];
					arrayInterno[i] = arrayInterno[this.indice];
					arrayInterno[this.indice] = null;
					this.indice = this.indice - 1;
				}
			}
		}
		return t;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		T t = null;
		for (int i = 0; i < arrayInterno.length; i = i + 1){
			if (arrayInterno[i] != null){
				if (arrayInterno[i].equals(o)){
					t = arrayInterno[i];
				}
			}
		}
		return t;

	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		if (this.indice == (-1)){
			return true;
		} else {
			return false;
		}
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		if (this.indice == (this.tamanho - 1)){
			return true;
		} else {
			return false;
		}
	}
	
	public T maximo() {
		if (this.isVazio()){
			return null;
		}
		T maximo = arrayInterno[0];
		for (int i = 1; i < arrayInterno.length; i = i + 1){
			if (arrayInterno[i] != null){
				if (comparadorMaximo.compare(maximo, arrayInterno[i]) > 0){
					maximo = arrayInterno[i];
				}
			}
		}
		return maximo;
	}

	public T minimo() {
		if (this.isVazio()){
			return null;
		}
		T minimo = arrayInterno[0];
		for (int i = 1; i < arrayInterno.length; i = i + 1){
			if (arrayInterno[i] != null){
				if (comparadorMinimo.compare(minimo, arrayInterno[i]) > 0){
					minimo = arrayInterno[i];
				}
			}
		}
		return minimo;
	}

}

class ComparadorMaximo implements Comparator<Aluno> {

	public int compare(Aluno o1, Aluno o2) {
		return (int) (o2.getMedia() - o1.getMedia());
	}

}

class ComparadorMinimo implements Comparator<Aluno> {

	public int compare(Aluno o1, Aluno o2) {
		return (int) (o1.getMedia() - o2.getMedia());
	}

}