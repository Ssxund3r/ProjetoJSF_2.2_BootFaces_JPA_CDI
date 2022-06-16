package br.com.ProjetoJsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;

@ViewScoped
@Named(value = "relusuario")
public class RelUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private Date dataIni;

	private Date dataFim;

	private String nome;

	@Inject
	private IDaoPessoa iDaoPessoa;
	
	@Inject
	private GenericDao<Pessoa> genericDao;
	
	public void buscarRelPessoa() {
		if (dataIni == null && dataFim == null && nome == null) {
			pessoas = genericDao.getListEntity(Pessoa.class);
		} else {
			pessoas = iDaoPessoa.relatorioPessoas(nome, dataIni, dataFim);
		}
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
