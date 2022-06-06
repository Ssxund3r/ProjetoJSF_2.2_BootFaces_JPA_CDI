package br.com.ProjetoJsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.repository.IDaoLancamento;

@ViewScoped
@Named(value = "rellancamento")
public class RelLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@Inject
	private IDaoLancamento daoLancamento;

	@Inject
	private GenericDao<Lancamento> genericDao;

	public void buscarLancamento() {

		lancamentos = genericDao.getListEntity(Lancamento.class);

	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
