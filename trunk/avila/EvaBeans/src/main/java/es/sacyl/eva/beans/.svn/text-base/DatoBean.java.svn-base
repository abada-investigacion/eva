package es.sacyl.eva.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class DatoBean implements Serializable {

	private static final long serialVersionUID = 2862391047278963119L;
	
	private String dato;
	private String titulo;
	private Collection<CodificacionBean> codigos;
	
	public DatoBean() {
		this.codigos = new ArrayList<CodificacionBean>();
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Collection<CodificacionBean> getCodigos() {
		return codigos;
	}

	@Override
	public String toString() {
		return "[dato=" + dato + ", titulo=" + titulo + ", " + codigos + "]";
	}
	
	
	
}
