package br.com.caelum.financas.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private String cidade;
	private String estado;
	
	//Getters and setters
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	
	
}
