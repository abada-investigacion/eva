package es.sacyl.eva.rest;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.containment.Container;

@Path("/cda")
public class CDAService {

	public static boolean activo = false;
	
	@POST
	@Path("/process")
	public Response createProductInJSON(String value) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		String identificador = sdf.format(new Date()) + "_" + value.length();
		
		// Generar el contenedor
		CDABean CDA = new CDABean(identificador, value);
		
//		String name = sdf.format(new Date()) + "-cda.xml";
//
//		try {
//			// Create file
//			FileWriter fstream = new FileWriter(name);
//			BufferedWriter out = new BufferedWriter(fstream);
//			out.write(value);
//			// Close the output stream
//			out.close();
//		} catch (Exception e) {// Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//
//		// String output = "CDA : " + msg;
		
		if (activo) {
			// Procesar
			// TODO
			// Devolver resultado
			return Response.status(200).entity("Procesado").build();
		} else {
			try {
				// Guardar
				Container.getInstance().addCDA(CDA);
				// Devolver resultado
				return Response.status(406).entity("Inactivo (almacenado para posterior proceso)").build();
			} catch (IOException e) {
				// Devolver que se ha producido un error
				return Response.status(500).entity("Se ha producido un error").build();
			}
		}

	}

}
