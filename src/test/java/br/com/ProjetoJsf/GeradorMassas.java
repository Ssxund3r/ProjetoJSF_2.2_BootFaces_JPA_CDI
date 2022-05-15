package br.com.ProjetoJsf;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;

public class GeradorMassas {
	static Faker faker = new Faker(Locale.ENGLISH);
	
	public static void main(String[] args) {
		GeradorMassas geradorMassas = new GeradorMassas();
		
		for(int i = 0; i < 5; i++) {
			geradorMassas.gerarContasElderScroll();
		}
		
		for(int i = 0; i< 5; i++) {
			geradorMassas.gerarContasLordOfTheRings();
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarContasElderScroll() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(faker.elderScrolls().city());
		pessoa.setSobrenome(faker.elderScrolls().dragon());
		pessoa.setIdade(faker.number().randomDigit());
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
		pessoa.setIdade(faker.number().randomDigit());
		pessoa.setLogin(faker.name().username());
		pessoa.setSenha(faker.internet().password());
		pessoa.setPerfilUser("ADMIN");
		genericDao.salvar(pessoa);

	}
	
}
