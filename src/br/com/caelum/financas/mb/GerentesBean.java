package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;
import br.com.caelum.financas.modelo.GerenteConta;

@Named
@ViewScoped
public class GerentesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private GerenteDao dao;

	private List<Gerente> gerentes;
	
	private Gerente gerente = new GerenteConta();

	
	//Getters and setters
	public GerenteDao getDao() {
		return dao;
	}

	public void setDao(GerenteDao dao) {
		this.dao = dao;
	}

	public List<Gerente> getGerentes() {
		if(this.gerentes == null) {
			this.gerentes = dao.lista();
		}
		return gerentes;
	}

	public void setGerentes(List<Gerente> gerentes) {
		this.gerentes = gerentes;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	public void grava() {
		if(this.gerente.getId() == null) {
			dao.adiciona(gerente);
		}else {
			dao.altera(gerente);
		}
		
		this.gerentes = dao.lista();
		System.out.println("Gravando o gerente: " + gerente.getNome());

		limpaFormularioDoJSF();
	}

	public void remove() {
		dao.busca(this.gerente.getId());
		
		dao.remove(this.gerente);
		this.gerentes = dao.lista();
		
		System.out.println("Removendo o gerente");

		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.gerente = new GerenteConta();

	}

}
