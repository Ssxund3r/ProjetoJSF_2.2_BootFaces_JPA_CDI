package br.com.threadfilageradormassas;

import java.awt.GridBagLayout;
import java.util.Locale;

import javax.swing.JPanel;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;

public class GeradorMassasThreadProcessamentoParalelo {	
	private static GeradorMassaFilaThreadImpl filaThreadImpl = new GeradorMassaFilaThreadImpl();
	private static Faker faker = new Faker(new Locale("pt-BR"));
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public GeradorMassasThreadProcessamentoParalelo() {
		for(int i = 0; i < 10; i++) {
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
		
		filaThreadImpl.start();
	}
	public static void main(String[] args) {
		GeradorMassasThreadProcessamentoParalelo geradorMassasThreadProcessamentoParalelo = new GeradorMassasThreadProcessamentoParalelo();

	}
	
}