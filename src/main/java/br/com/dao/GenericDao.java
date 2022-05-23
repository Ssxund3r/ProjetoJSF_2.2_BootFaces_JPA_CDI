package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class GenericDao<E> {

	private EntityManager entityManager = JPAUtil.getEntityManager();

	public void salvar(E entidade) {

		entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(entidade);

		entityTransaction.commit();
		entityManager.close();

	}

	public E merge(E entidade) {

		entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = entityManager.merge(entidade);

		entityTransaction.commit();
		entityManager.close();

		return retorno;

	}

	@SuppressWarnings("unchecked")
	public E consulta(E entidade) {
		Object id = JPAUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;
	}

	public E consultaAlternativa(Long id, Class<E> entidade) { /*
																 * Versão alternativa para Consulta do ID - Ao invés de
																 * passar 2 classes, então passamos 2 parâmetros.
																 */
		E e = (E) entityManager.find(entidade, id);
		return e;

	}

	public E consultar(Class<E> entidade, String PK) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E objeto = (E) entityManager.find(entidade, Long.parseLong(PK));
		entityTransaction.commit();
		return objeto;
	}

	public void delete(E entidade) {
		entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.remove(entidade);

		entityTransaction.commit();
		entityManager.close();

	}

	public void deletePorID(E entidade) {
		entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();
		entityManager.close();

	}

	@SuppressWarnings("unchecked")
	public List<E> getListEntity(Class<E> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		entityTransaction.commit();
		entityManager.close();

		return retorno;
	}
}
