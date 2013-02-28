package es.sacyl.eva.containment;

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
