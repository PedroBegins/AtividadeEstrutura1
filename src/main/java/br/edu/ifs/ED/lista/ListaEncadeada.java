package br.edu.ifs.ED.lista;

import br.edu.ifs.ED.no.No;

import java.sql.Array;
import java.util.Arrays;

public class ListaEncadeada<T extends Comparable<T>> extends Lista<T> {

	private No<T> inicio;
	private No<T> ultimo;
	private int tamanho;

	@Override
	public void incluir(T elemento) throws Exception {

		No<T> celula = new No<T>(elemento);

		if (tamanho == 0) {
			this.inicio = celula;
			this.ultimo = celula;

		} else {
			this.ultimo.setProximo(celula);
		}

		this.ultimo = celula;
		this.tamanho++;

	}

	@Override
	public T get(int posicao) throws Exception {
		No<T> atual = this.inicio;
		for (int i = 0; i < posicao; i++) {
			if (atual.getProximo() != null) {
				atual = atual.getProximo();
			}
		}
		return atual.getValor();

	}

	@Override
	public int getPosElemento(T elemento) throws Exception {

		No<T> atual= this.inicio;

		int count=0;

		for (int i = 0; i <this.tamanho ; i++) {

			if (elemento.equals(atual.getValor())){

				count=i;
				break;

			}

			atual=atual.getProximo();

		}

		return count;
	}



	@Override
	public void remover(int posicao) throws Exception {

		No<T> atual=this.inicio;
		No<T> anterior= null;

		if(posicao==0 && this.tamanho==1){
			this.inicio=null;
			this.ultimo=null;

			this.tamanho--;

		} else if (posicao==0 && tamanho>1) {

			this.inicio=atual.getProximo();
			tamanho--;

		}else if (posicao>0 && tamanho>1) {


			for (int i = 0; i < posicao; i++) {


				if (i == posicao - 1) {
					anterior = atual;
				}

				atual = atual.getProximo();
			}

			if (atual == ultimo) {
				ultimo = anterior;
			}

			anterior.setProximo(atual.getProximo());
			tamanho--;


		}

		}

	@Override
	public void limpar() {

		this.inicio=null;
		this.ultimo=null;
		this.tamanho=0;

	}

	@Override
	public int getTamanho() {
		return this.tamanho;
	}

	@Override
	public boolean contem(T elemento) throws Exception {
		No<T> atual = this.inicio;

		for (int i = 0; i < this.tamanho; i++) {

			if (elemento.equals(atual.getValor())) {
				return true;
			}
			atual = atual.getProximo();
		}

		return false;
	}

	@Override
	public int compareTo(Lista<T> item) {
		return 0;
	}

	@Override
	public T[] TransformarEmVetor() {
		return vetorRecursivo( inicio);
	}

	@Override
	public String toString() {
		return "ListaEncadeada [inicio=" + inicio + "]";
	}

	public T[] vetorRecursivo( No<T> aux) {

		if (aux == null) {
			return (T[]) new Object[0]; // Retorna um vetor vazio se a lista estiver vazia
		} else {
			T[] vetor = vetorRecursivo(aux.getProximo());
			T[] novoVetor = Arrays.copyOf(vetor, vetor.length + 1);
			novoVetor[vetor.length] = aux.getValor();
			return novoVetor;
		}
	}






}
