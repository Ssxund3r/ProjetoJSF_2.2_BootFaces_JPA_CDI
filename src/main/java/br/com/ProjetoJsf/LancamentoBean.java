package br.com.ProjetoJsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dao.GenericDao;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {

	private Lancamento lancamento = new Lancamento();
	private GenericDao<Lancamento> genericDao = new GenericDao<Lancamento>();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
		HttpSession session = req.getSession();
		Pessoa pessoaUser = (Pessoa) session.getAttribute("usuarioLogado");

		lancamento.setUsuario(pessoaUser);
		genericDao.salvar(lancamento);
		return "";
	}
	
	public String novo() {
	
		return "";
	}
	
	public String remover () {
		
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
