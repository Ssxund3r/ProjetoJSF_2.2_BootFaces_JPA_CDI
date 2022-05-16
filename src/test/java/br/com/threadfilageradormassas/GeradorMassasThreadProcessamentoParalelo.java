package br.com.threadfilageradormassas;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;

public class GeradorMassasThreadProcessamentoParalelo {	
	
	private static GeradorMassaFilaThreadImpl filaThreadImpl = new GeradorMassaFilaThreadImpl();
	private static Faker faker = new Faker(new Locale("pt-BR"));
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GeradorMassasThreadProcessamentoParalelo geradorMassasThreadProcessamentoParalelo = 
				new GeradorMassasThreadProcessamentoParalelo();
	}
	
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public GeradorMassasThreadProcessamentoParalelo() { //Executa o que estiver dentro no momento 
													    //da abertura ou execução		
		for(int i = 0; i < 5; i++) {
			GenericDao genericDao = new GenericDao();
			Pessoa pessoa = new Pessoa();
			
			pessoa.setNome(faker.elderScrolls().city() + i);
			pessoa.setSobrenome(faker.elderScrolls().dragon() + i);
			pessoa.setIdade(faker.number().numberBetween(18, 99) + i);
			pessoa.setLogin(faker.name().username() + i);
			pessoa.setSenha(faker.internet().password() + i);
			pessoa.setPerfilUser("ADMIN");
			genericDao.salvar(pessoa);
			
			filaThreadImpl.add(pessoa);			
		}	
		
		for(int i = 0; i < 5; i++) {
			GenericDao genericDao = new GenericDao();
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(faker.name().firstName());
			pessoa.setSobrenome(faker.name().lastName());
			pessoa.setIdade(faker.number().numberBetween(18, 99));
			pessoa.setLogin(faker.name().username());
			pessoa.setSenha(faker.internet().password());
			pessoa.setPerfilUser("ADMIN");
			genericDao.salvar(pessoa);
			
			filaThreadImpl.add(pessoa);	
		}
		
		for(int i = 0; i < 5; i++) {
			GenericDao genericDao = new GenericDao();
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(faker.lordOfTheRings().character());
			pessoa.setSobrenome(faker.lordOfTheRings().location());
			pessoa.setIdade(faker.number().numberBetween(18, 99));
			pessoa.setLogin(faker.name().username());
			pessoa.setSenha(faker.internet().password());
			pessoa.setPerfilUser("ADMIN");
			genericDao.salvar(pessoa);
			
			filaThreadImpl.add(pessoa);	
		}
		
		filaThreadImpl.start();
	}
}