package es.sacyl.eva.containment;

/*
 * #%L
 * Eva REST-Jimena
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.sacyl.eva.beans.CDABean;

public class Container {

	/* SINGLETON */
	private static Container container = null;
	private Container() {
	}
	public static Container getInstance() {
		if (container == null) {
			container = new Container();
		}
		return container;
	}
	
	/* CONFIG */
	private String ruta = "./CDAs/";

	public void addCDA(CDABean CDA) throws IOException {
		
		// Comprobar directorio
		File directorio = new File(ruta);
		if (!directorio.exists()) {
			directorio.mkdir();
		}
		
		File fichero = new File(ruta + CDA.getIdentificador() + ".cda");
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
		salida.writeObject(CDA);
	    salida.close();
		
	}
	
	public Collection<CDABean> getCDAs() throws IOException, ClassNotFoundException {
		
		List<CDABean> lista = new ArrayList<CDABean>();

		File directorio = new File(ruta);
		if (directorio.exists()) {
			// Si existe el fichero, comprobamos
			
			for (File fichero : directorio.listFiles()) {
				if (fichero.getName().toLowerCase().endsWith(".cda")) {
					ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero));
					lista.add((CDABean) entrada.readObject());
				}
			}
		}
		
		return lista;
		
	}
	
}
