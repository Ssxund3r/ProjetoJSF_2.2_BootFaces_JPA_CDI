package br.com.ProjetoJsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();

	public String salvar() {
		pessoa = genericDao.merge(pessoa);

		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {
		genericDao.deletePorID(pessoa);
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public GenericDao<Pessoa> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao<Pessoa> genericDao) {
		this.genericDao = genericDao;
	}

}
