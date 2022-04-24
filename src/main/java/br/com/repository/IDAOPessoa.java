package br.com.repository;

import br.com.entidades.Pessoa;

public interface IDAOPessoa {
	
	Pessoa consultaUsuario(String login, String senha);
	
}
