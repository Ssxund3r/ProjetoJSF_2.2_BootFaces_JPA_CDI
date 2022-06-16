package br.com.repository;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;

public interface IDaoPessoa {
	
	Pessoa consultaUsuario(String login, String senha);
	
	List<SelectItem> listaEstados();
	
	List<Pessoa> relatorioPessoas(String nome, Date dataIni, Date dataFim);
	
}
