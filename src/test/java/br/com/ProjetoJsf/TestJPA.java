package br.com.ProjetoJsf;

import java.util.List;
import java.util.Locale;

import javax.persistence.Persistence;

import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;

public class TestJPA {
	static Faker faker = new Faker(Locale.ENGLISH);

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGerarDados() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(faker.name().firstName());
		pessoa.setSobrenome(faker.name().fullName());
		pessoa.setIdade(faker.number().randomDigit());
		pessoa.setPerfilUser("ADMIN");
		genericDao.salvar(pessoa);

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
	public void testAtualizarID() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = genericDao.consultaAlternativa(76L, Pessoa.class);

		pessoa.setIdade(123);
		pessoa.setNome("JavaFaker");

		pessoa = genericDao.merge(pessoa);
		System.out.println(pessoa);

	}

	@Test
	public void testDeletarPorID() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		Pessoa pessoa = genericDao.consultaAlternativa(76L, Pessoa.class);

		genericDao.deletePorID(pessoa);
		System.out.println(pessoa);

	}

	@Test
	public void testConsultaListaPorID() {
		GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
		List<Pessoa> list = genericDao.getListEntity(Pessoa.class);
		for (Pessoa pessoa : list) {
			System.out.println(pessoa);
			System.out.println("------------");
		}
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Test
	public void testInserirLancamento() {
		GenericDao genericDao = new GenericDao();
		Pessoa pessoa = (Pessoa) genericDao.consultaAlternativa(76L, Pessoa.class);

		Lancamento lancamento = new Lancamento();

		lancamento.setEmpresaOrigem("abc");
		lancamento.setEmpresaDestino("cba");
		lancamento.setNumeroNotaFiscal("12");
		lancamento.setUsuario(pessoa);

		genericDao.salvar(lancamento);

	}
}