package es.sacyl.eva.rest;

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
