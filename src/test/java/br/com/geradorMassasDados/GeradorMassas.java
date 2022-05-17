package br.com.geradorMassasDados;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;

public class GeradorMassas {
	private static Faker faker = new Faker(new Locale("pt-BR"));
	
	public static void main(String[] args) {
		GeradorMassas geradorMassas = new GeradorMassas();
		
		for(int i = 0; i < 5; i++) {
			geradorMassas.gerarContasElderScroll();
		}
		
		for(int i = 0; i < 5; i++) {
			geradorMassas.gerarContasLordOfTheRings();
		}
		
		for(int i = 0; i < 5; i++) {
			geradorMassas.gerarContasBrasileiros();
		}
		
		for(int i = 0; i < 100; i++) {
			geradorMassas.gerarLancamentos();
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarContasElderScroll() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(faker.elderScrolls().firstName());
		pessoa.setSobrenome(faker.elderScrolls().dragon());
		pessoa.setIdade(faker.number().numberBetween(18, 99));
		pessoa.setLogin(faker.name().username());
		pessoa.setSenha(faker.internet().password());
		pessoa.setPerfilUser("ADMIN");
		genericDao.salvar(pessoa);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarContasLordOfTheRings() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(faker.lordOfTheRings().character());
		pessoa.setSobrenome(faker.lordOfTheRings().location());
		pessoa.setIdade(faker.number().numberBetween(18, 99));
		pessoa.setLogin(faker.name().username());
		pessoa.setSenha(faker.internet().password());
		pessoa.setPerfilUser("ADMIN");
		genericDao.salvar(pessoa);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarContasBrasileiros() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(faker.name().firstName());
		pessoa.setSobrenome(faker.name().lastName());
		pessoa.setIdade(faker.number().numberBetween(18, 99));
		pessoa.setLogin(faker.name().username());
		pessoa.setSenha(faker.internet().password());
		pessoa.setPerfilUser("ADMIN");
		genericDao.salvar(pessoa);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarLancamentos() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = (Pessoa) genericDao.consultaAlternativa(41L, Pessoa.class);

		Lancamento lancamento = new Lancamento();

		lancamento.setEmpresaOrigem(faker.company().industry());
		lancamento.setEmpresaDestino(faker.commerce().department());
		lancamento.setNumeroNotaFiscal(faker.number().numberBetween(100, 9999)+faker.number().numberBetween(100, 999));
		lancamento.setUsuario(pessoa);

		genericDao.salvar(lancamento);
		
	}
}
