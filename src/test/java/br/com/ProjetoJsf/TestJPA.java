package br.com.ProjetoJsf;

import javax.persistence.Persistence;

import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;

public class TestJPA {
	
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
	}
	
	@Test
	public void testInserirNovoUsuario() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("lorem");
		pessoa.setSobrenome("Ipsum");
		pessoa.setIdade(999);
		pessoa.setSexo("NAO");
		pessoa.setLogin("t");
		pessoa.setSenha("t");
		
		genericDao.salvar(pessoa);
		
	}

}
