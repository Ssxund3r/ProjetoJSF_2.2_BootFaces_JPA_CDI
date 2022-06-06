package br.com.ProjetoJsf;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

import br.com.dao.GenericDao;
import br.com.entidades.Cidades;
import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;
import br.com.repository.IDaoPessoa;
import net.bootsfaces.component.selectOneMenu.SelectOneMenu;

@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();

	@Inject
	private GenericDao<Pessoa> genericDao;

	@Inject
	private IDaoPessoa idaoPessoa;

	@Inject
	private JPAUtil jpaUtil;

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private List<SelectItem> estados;

	private List<SelectItem> cidades;

	private Part arquivofoto;

	public String salvar() throws IOException {

		if (arquivofoto.getInputStream() != null) {
			/* Processar imagem */

			byte[] imagemByte = getByte(arquivofoto.getInputStream());

			/* transformar em um bufferimage */
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagemByte));

			if (bufferedImage != null) {

				pessoa.setFotoIconBase64Original(imagemByte); /* Salva imagem original */
				/* Pega o tipo da imagem */
				int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

				int largura = 200;
				int altura = 200;

				/* Criar a miniatura */
				BufferedImage resizedImage = new BufferedImage(largura, altura, type);
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(bufferedImage, 0, 0, largura, altura, null);
				g.dispose();

				/* Escrever novamente a imagem em tamanho menor */
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				String extensao = arquivofoto.getContentType().split("\\/")[1]; /* image/png */
				ImageIO.write(resizedImage, extensao, baos);

				String miniImagem = "data:" + arquivofoto.getContentType() + ";base64,"
						+ DatatypeConverter.printBase64Binary(baos.toByteArray());

				/* Processar imagem */
				pessoa.setFotoIconBase64(miniImagem);
				pessoa.setExtensao(extensao);
			}

		} 

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
		/* Executa um processo antes de um novo registro */
		pessoa = new Pessoa();
		return "";
	}

	public String limpar() {
		/* Executa um processo antes de limpar o registro */
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
		pessoas = genericDao.getListEntityLimit10(Pessoa.class);
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

	@SuppressWarnings("static-access")
	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
		HttpSession session = req.getSession();

		session.removeAttribute("usuarioLogado");

		req = (HttpServletRequest) context.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().invalidate();

		return "index.jsf";
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
		} else {
			FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Usuário não encontrado!"));
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

		try {
			URL url = new URL("http://viacep.com.br/ws/" + pessoa.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = bufferedReader.readLine()) != null) {
				jsonCep.append(cep);
			}

			Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);

			pessoa.setCep(gsonAux.getCep());
			pessoa.setLogradouro(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			pessoa.setIbge(gsonAux.getIbge());
			pessoa.setGia(gsonAux.getGia());
			pessoa.setDdd(gsonAux.getDdd());
			pessoa.setSiafi(gsonAux.getSiafi());

		} catch (Exception e) {
			e.printStackTrace();
			mostrarMsg("Erro ao consultar cep!");
		}

	}

	public List<SelectItem> getEstados() {
		estados = idaoPessoa.listaEstados();
		return estados;
	}

	@SuppressWarnings({ "unchecked" })
	public void carregaCidades(AjaxBehaviorEvent event) {

		Estados estado = (Estados) ((SelectOneMenu) event.getSource()).getValue();

		if (estado != null) {
			pessoa.setEstados(estado);

			List<Cidades> cidades = jpaUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " + estado.getId()).getResultList();

			List<SelectItem> selectItemsCidade = new ArrayList<SelectItem>();

			for (Cidades cidade : cidades) {
				selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
			}

			setCidades(selectItemsCidade);
		}

	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

	public List<SelectItem> getCidades() {

		return cidades;
	}

	@SuppressWarnings("unchecked")
	public String editar() {
		if (pessoa.getCidades() != null) {
			Estados estados = pessoa.getCidades().getEstados();
			pessoa.setEstados(estados);

			List<Cidades> cidades = jpaUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " + estados.getId()).getResultList();

			List<SelectItem> selectItemsCidade = new ArrayList<SelectItem>();

			for (Cidades cidade : cidades) {
				selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
			}

			setCidades(selectItemsCidade);

		}

		return "";
	}

	public Part getArquivofoto() {
		return arquivofoto;
	}

	public void setArquivofoto(Part arquivofoto) {
		this.arquivofoto = arquivofoto;
	}

	/* Método que converte um inputstream para array de bytes */
	private byte[] getByte(InputStream is) throws IOException {
		int len;
		int size = 1024;
		byte[] buf = null;
		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];

			while ((len = is.read(buf, 0, size)) != -1) {
				bos.write(buf, 0, len);
			}

			buf = bos.toByteArray();

		}

		return buf;
	}

	public void download() throws IOException {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String fileDownloadId = params.get("fileDownloadId");

		Pessoa pessoa = genericDao.consultar(Pessoa.class, fileDownloadId);

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();

		response.addHeader("Content-Disposition", "attachment; filename=download." + pessoa.getExtensao());
		response.setContentType("application/octet-stream");
		response.setContentLength(pessoa.getFotoIconBase64Original().length);
		response.getOutputStream().write(pessoa.getFotoIconBase64Original());
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete();

	}

}
