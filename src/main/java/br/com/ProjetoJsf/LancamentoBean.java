package br.com.ProjetoJsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoLancamento;

@ViewScoped
@Named(value = "lancamentoBean")
public class LancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@Inject
	private GenericDao<Lancamento> genericDao;

	@Inject
	private IDaoLancamento daoLancamento;

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
		HttpSession session = req.getSession();
		Pessoa pessoaUser = (Pessoa) session.getAttribute("usuarioLogado");

		lancamento.setUsuario(pessoaUser);
		lancamento = genericDao.merge(lancamento);

		carregarLancamentos();

		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Salvo com sucesso!"));

		return "";
	}

	public String novo() {
		lancamento = new Lancamento();
		return "";
	}

	@PostConstruct
	public void carregarLancamentos() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
		HttpSession session = req.getSession();
		Pessoa pessoaUser = (Pessoa) session.getAttribute("usuarioLogado");

		lancamentos = daoLancamento.consultarLimite10(pessoaUser.getId());

	}

	public String remove() {
		genericDao.deletePorID(lancamento);
		lancamento = new Lancamento();
		carregarLancamentos();
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Excluido com sucesso!"));
		return "";
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public GenericDao<Lancamento> getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao<Lancamento> genericDao) {
		this.genericDao = genericDao;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
