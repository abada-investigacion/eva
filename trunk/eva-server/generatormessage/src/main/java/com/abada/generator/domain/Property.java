/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.generator.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *conexion bd, sinonimos,dblink, acceso property
 *
 * @author david
 */
public class Property {

    private static Log log = LogFactory.getLog(Property.class);
    private Properties property;


    /**
     * Creates the DB connection, property, dblinks and synonyms (if needed)
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Property() throws IOException{
       
        //Load Properties File
        property = new Properties();
        InputStream fileproperty;
        try {
            fileproperty = new FileInputStream("messages.properties");
        } catch (FileNotFoundException ex) {
            log.error("\n" + ex.getMessage() + " \n looking /com/abada/generatormessage/properties/messages.properties...");
            fileproperty = this.getClass().getResourceAsStream("/com/abada/generatormessage/properties/messages.properties");
        }
        property.load(fileproperty);

       
    }
    /**
     * Returns property
     * @return
     */
    public Properties getProperty() {
        return property;
    }

    /**
     * Return connections
     * @return
     */
   
    
}
