package es.sacyl.eva.beans;

/*
 * #%L
 * Eva beans
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
