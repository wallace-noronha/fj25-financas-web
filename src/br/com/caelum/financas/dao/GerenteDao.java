package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Gerente;
import br.com.caelum.financas.modelo.GerenteConta;

@Stateless
public class GerenteDao {

	@Inject
	private EntityManager manager;
	
	public void adiciona(Gerente gerente) {
		this.manager.joinTransaction();
		this.manager.persist(gerente);
	}
	
	public void remove(Gerente gerente) {
		this.manager.joinTransaction();
		Gerente gerenteParaRemover = this.manager.find(Gerente.class, gerente.getId());
		this.manager.remove(gerenteParaRemover);
	}
	
	public void altera(Gerente gerente) {
		this.manager.joinTransaction();
		manager.merge(gerente);
	}
	
	public List<Gerente> lista() {
		return this.manager.createQuery("select g from Gerente g", Gerente.class)
				.getResultList();
	}
	
	public GerenteConta busca(Integer id) {
		return this.manager.find(GerenteConta.class, id);
	}
}
