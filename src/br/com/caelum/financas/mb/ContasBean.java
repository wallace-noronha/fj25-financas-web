package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.GerenteConta;

@Named
@ViewScoped
public class ContasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Inject
    private ContaDao contaDao;
    @Inject
    private MovimentacaoDao movimentacaoDao;
    @Inject
    private GerenteDao gerenteDao;
	private Conta conta = new Conta();
	private List<Conta> contas;
	private Integer gerenteId;

	public GerenteDao getGerenteDao() {
		return gerenteDao;
	}

	public void setGerenteDao(GerenteDao gerenteDao) {
		this.gerenteDao = gerenteDao;
	}

	public Integer getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Integer gerenteId) {
		this.gerenteId = gerenteId;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void grava() {
//		if(gerenteId != null) {
//			Gerente gerenteRelacionamento = gerenteDao.busca(gerenteId);
//			this.conta.setGerente(gerenteRelacionamento);
//		}
		if(gerenteId != null) {
			GerenteConta gerenteRelacionado = gerenteDao.busca(gerenteId);
			gerenteRelacionado.setNumeroDaConta(this.conta.getNumero());
			this.conta.setGerente(gerenteRelacionado);
		}
		if(this.conta.getId() == null) {
			contaDao.adiciona(conta);
		}else {
			contaDao.altera(conta);
		}
		
		this.contas = contaDao.lista();
		System.out.println("Gravando a conta do títular" + conta.getTitular());

		limpaFormularioDoJSF();
	}

	public List<Conta> getContas() {
		if(this.contas == null) {
			this.contas = contaDao.lista();
		}
		System.out.println("Listando as contas");

		return this.contas;
	}

	public void remove() {
		contaDao.busca(this.conta.getId());
		
		contaDao.remove(this.conta);
		this.contas = contaDao.lista();
		
		System.out.println("Removendo a conta");

		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
		gerenteId = null;
	}
	
}
