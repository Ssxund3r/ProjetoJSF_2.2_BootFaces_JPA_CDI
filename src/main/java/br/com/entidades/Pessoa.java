package br.com.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String sobrenome;

	@NotNull(message = "O campo Idade é obrigatório!")
	private Integer idade;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento = new Date();

	private String sexo;

	private String[] frameworks;

	private Boolean ativo;

	private String login;

	private String senha;

	private String perfilUser;

	private String nivelProgramador;

	private Integer[] linguagensProg;

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String unidade;
	private String ibge;
	private String gia;

	public Pessoa() {

	}

	public Pessoa(Long id, String nome, String sobrenome, Integer idade, Date dataNascimento, String sexo,
			String[] frameworks, Boolean ativo, String login, String senha, String perfilUser, String nivelProgramador,
			Integer[] linguagensProg, String cep, String logradouro, String bairro, String localidade, String uf,
			String unidade, String ibge, String gia) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.frameworks = frameworks;
		this.ativo = ativo;
		this.login = login;
		this.senha = senha;
		this.perfilUser = perfilUser;
		this.nivelProgramador = nivelProgramador;
		this.linguagensProg = linguagensProg;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.unidade = unidade;
		this.ibge = ibge;
		this.gia = gia;
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

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
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

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade
				+ ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", frameworks="
				+ Arrays.toString(frameworks) + ", ativo=" + ativo + ", login=" + login + ", senha=" + senha
				+ ", perfilUser=" + perfilUser + ", nivelProgramador=" + nivelProgramador + ", linguagensProg="
				+ Arrays.toString(linguagensProg) + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", uf=" + uf + ", unidade=" + unidade + ", ibge=" + ibge + ", gia="
				+ gia + "]";
	}

}
