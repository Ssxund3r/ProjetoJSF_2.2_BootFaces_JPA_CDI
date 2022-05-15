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
	
	@Test
	public void testConsultaId() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(76L);	
		pessoa = genericDao.consulta(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeConsultaIdAlternatio() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = genericDao.consultaAlternativa(76L, Pessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testDeletarPorID() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = genericDao.consultaAlternativa(76L, Pessoa.class);
		
		genericDao.deletePorID(pessoa);
		System.out.println(pessoa);
		
	}
	
	
}
