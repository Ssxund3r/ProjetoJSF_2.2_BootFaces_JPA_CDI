package br.com.ProjetoJsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dao.GenericDao;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaImpl;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private IDaoPessoa idaoPessoa = new IDaoPessoaImpl();

	public String salvar() {
		pessoa = genericDao.merge(pessoa);
		carregarPessoas();
		mostrarMsg("Cadastrado com sucesso!");
		return "";
	}

	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);

	}

	public String novo() {
		/*Executa um processo antes de um novo registro*/
		pessoa = new Pessoa();
		return "";
	}
	
	public String limpar() {
		/*Executa um processo antes de limpar o registro*/
		pessoa = new Pessoa();
		return "";
	}
	

	public String remove() {
		genericDao.deletePorID(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		mostrarMsg("Removido com sucesso!");
		return "";
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = genericDao.getListEntity(Pessoa.class);
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public String logar() {

		carregarPessoas();
		Pessoa pessoaUser = idaoPessoa.consultaUsuario(pessoa.getLogin(), pessoa.getSenha());

		if (pessoaUser != null) { // Achou o usuário

			// adicionar o usuário na sessão [usuarioLogado]
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();

			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = req.getSession();

			session.setAttribute("usuarioLogado", pessoaUser);

			return "primeirapagina.jsf";
		}
		return "index.jsf";
	}

	public boolean permiteAcesso(String acesso) {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
		HttpSession session = req.getSession();
		Pessoa pessoaUser = (Pessoa) session.getAttribute("usuarioLogado");

		return pessoaUser.getPerfilUser().equals(acesso);
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {
		System.out.println("Metodo pesquisa cep chamado CEP:" + pessoa.getCep());
	} 
	

}
