package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;

//@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class ContaDao {
	
//	@Resource
//	private UserTransaction ut;
	
	@Inject
	EntityManager manager;

	public void adiciona(Conta conta) {
//		this.manager.joinTransaction();
		this.manager.persist(conta);
	}

	public Conta busca(Integer id) {
		return this.manager.find(Conta.class, id);
	}

	public List<Conta> lista() {
		return this.manager.createQuery("select c from Conta c", Conta.class)
				.getResultList();
	}

	public void remove(Conta conta) {
		this.manager.joinTransaction();
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.remove(contaParaRemover);
	}
	
	public void altera(Conta conta) {
		this.manager.joinTransaction();
		manager.merge(conta);
		
	}
	
	public int trocaNomeDoBancoEmLote(String antigoNomeBanco, String novoNomeBanco) {
		String jpql = "UPDATE Conta c SET c.banco = :novoNome WHERE c.banco = :antigoNome";
		Query query = manager.createQuery(jpql);
		query.setParameter("antigoNome", antigoNomeBanco);
		query.setParameter("novoNome", novoNomeBanco);
		return query.executeUpdate();
	}

}




