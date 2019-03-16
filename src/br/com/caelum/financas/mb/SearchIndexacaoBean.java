package br.com.caelum.financas.mb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

@Named
@RequestScoped
public class SearchIndexacaoBean {

	@Inject
	EntityManager manager;
	
	public void indexar() throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(manager);
		fullTextEntityManager.createIndexer().startAndWait();
	}
	
}
