package vetor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vetor.ComparadorMaximo;
import vetor.ComparadorMinimo;
import vetor.Vetor;

public class TestarVetor {
	
	private Vetor<Aluno> vetor;
	private ComparadorMaximo compMax;
	private ComparadorMinimo compMin;
	private Aluno victor;
	private Aluno eduardo;
	private Aluno borges;
	private Aluno araujo;
	
	
	@Before
	public void setUp() {
		this.vetor = new Vetor<Aluno>(5);
		
		this.compMax = new ComparadorMaximo();
		vetor.setComparadorMaximo(compMax);
		
		this.compMin = new ComparadorMinimo();
		vetor.setComparadorMinimo(compMin);
		
		this.victor = new Aluno("Victor", 10.0);
		this.eduardo = new Aluno("Eduardo", 9.5);
		this.borges = new Aluno("Borges", 9);
		this.araujo = new Aluno("Araujo", 8);
	}
	
	@Test
	public void testVetor(){
		
		//Testando isVazio() quando vazio;
		assertTrue(vetor.isVazio());
		
		//Testando inserir();
		vetor.inserir(this.eduardo);
		vetor.inserir(this.araujo);
		vetor.inserir(this.borges);
		vetor.inserir(this.victor);
		
		//Testando isVazio() quando nao vazio;
		assertEquals(vetor.isVazio(), false);
		
		//Testando remover();
		vetor.remover(eduardo);
		assertEquals(vetor.procurar(eduardo), null);
		
		//Testando procurar();
		assertEquals(vetor.procurar(victor), this.victor);
		assertEquals(vetor.procurar(borges), this.borges);
		assertEquals(vetor.procurar(araujo), this.araujo);
		assertEquals(vetor.procurar(eduardo), null);
		
		//Testando isCheio() quando nao cheio;
		assertEquals(vetor.isCheio(), false);
		
		vetor.inserir(this.eduardo);
		Aluno maria = new Aluno("Maria", 8.5);
		vetor.inserir(maria);
		
		//Testando isCheio quando cheio;
		assertEquals(vetor.isCheio(), true);
		
		//Testando maximo();
		assertEquals(vetor.maximo(), victor);
		
		//Testando minimo();
		assertEquals(vetor.minimo(), araujo);
	}
}
