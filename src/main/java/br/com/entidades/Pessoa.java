package br.com.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Pattern(regexp = "[a-zA-Z ]*", message = "Caracter inválido!")
	@NotNull(message = "O campo Nome é obrigatório!")
	private String nome;

	@Pattern(regexp = "[a-zA-Z ]*", message = "Caracter inválido!")
	@NotNull(message = "O campo Sobrenome é obrigatório!")
	@NotEmpty(message = "O campo sobrenome é obrigatório!")
	private String sobrenome;

	private Integer idade;

	@NotEmpty(message = "O campo CPF é obrigatório! ")
	@NotNull(message = "O campo CPF é obrigatório!")
	private String cpf;

	@NotEmpty(message = "O campo título de eleitor é obrigatório! ")
	@NotNull(message = "O campo título de eleitor é obrigatório!")
	private String titEleitoral;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento = new Date();

	@NotNull(message = "O campo sexo é obrigatório!")
	@NotEmpty(message = "O cmapo sexo é obrigatório!")
	private String sexo;

	private String[] frameworks;

	private Boolean ativo;

	@NotEmpty(message = "O campo login é obrigatório! ")
	@NotNull(message = "O campo login é obrigatório!")
	private String login;

	@NotEmpty(message = "O campo senha é obrigatório! ")
	@NotNull(message = "O campo senha é obrigatório!")
	private String senha;

	private String perfilUser;

	private String nivelProgramador;

	private Integer[] linguagensProg;

	@NotEmpty(message = "O campo CEP é obrigatório! ")
	@NotNull(message = "O campo CEP é obrigatório!")
	private String cep;

	private String numero;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String localidade;

	private String uf;

	private String ibge;

	private String gia;

	private String ddd;

	private String siafi;

	@ManyToOne
	private Cidades cidades;

	@Transient // Não fica persistente ou não grava no banco de dados...
	private Estados estados;

	@Column(columnDefinition = "text") // Tipo text grava arquivos em base64
	private String fotoIconBase64;

	private String extensao; // extensão jpg, png, jpeg...

	@Lob // Gravar arquivos no banco de dados
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original;

	public Pessoa() {

	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public Cidades getCidades() {
		return cidades;
	}

	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}

	public void setPerfilUser(String perfilUser) {
		this.perfilUser = perfilUser;
	}

	public String getPerfilUser() {
		return perfilUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTitEleitoral() {
		return titEleitoral;
	}

	public void setTitEleitoral(String titEleitoral) {
		this.titEleitoral = titEleitoral;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setFrameworks(String[] frameworks) {
		this.frameworks = frameworks;
	}

	public String[] getFrameworks() {
		return frameworks;
	}

	public String getSexo() {
		return sexo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setNivelProgramador(String nivelProgramador) {
		this.nivelProgramador = nivelProgramador;
	}

	public String getNivelProgramador() {
		return nivelProgramador;
	}

	public void setLinguagensProg(Integer[] linguagensProg) {
		this.linguagensProg = linguagensProg;
	}

	public Integer[] getLinguagensProg() {
		return linguagensProg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

}
