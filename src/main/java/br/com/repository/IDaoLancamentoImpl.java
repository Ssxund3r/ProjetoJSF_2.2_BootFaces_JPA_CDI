package br.com.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;

@Named
public class IDaoLancamentoImpl implements IDaoLancamento, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultar(Long codUser) {
		List<Lancamento> lista = null;

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser).getResultList();

		transaction.commit();

		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultarLimite10(Long codUser) {
		List<Lancamento> lista = null;

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser + " order by id desc ")
				.setMaxResults(10).getResultList();

		transaction.commit();

		return lista;
	}

	@Override
	public List<Lancamento> relatorioLancamento(String numNota, Date dataIni, Date dataFim) {
		
		
		
		return null;
	}
	
	

}
