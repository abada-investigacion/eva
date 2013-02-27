package es.sacyl.eva.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class CDABean implements Serializable {

	private static final long serialVersionUID = 6931109024407929908L;
	
	private String cda;
	private String identificador;
	private long creacion;
	private boolean procesado;
	
	public CDABean(String id, String CDA) {
		this.identificador = id;
		this.cda = CDA;
		this.creacion = System.currentTimeMillis();
		this.datos = new ArrayList<DatoBean>();
		this.procesado = false;
	}
	
	private String nombre;
	private String tarjeta;
	private String nhc;
	private String nss;
	private String dni;
	private Collection<DatoBean> datos;

	public boolean isProcesado() {
		return procesado;
	}
	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getNhc() {
		return nhc;
	}
	public void setNhc(String nhc) {
		this.nhc = nhc;
	}
	public String getNss() {
		return nss;
	}
	public void setNss(String nss) {
		this.nss = nss;
	}
	public String getCda() {
		return cda;
	}
	public String getIdentificador() {
		return identificador;
	}
	public long getCreacion() {
		return creacion;
	}
	public Collection<DatoBean> getDatos() {
		return datos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@Override
	public String toString() {
		return "CDABean [cda=" + cda + ", identificador=" + identificador + ", creacion=" + creacion + ", procesado="
				+ procesado + ", nombre=" + nombre + ", tarjeta=" + tarjeta + ", nhc=" + nhc + ", nss=" + nss
				+ ", dni=" + dni + ", " + datos + "]";
	} 
	
	
	
}
