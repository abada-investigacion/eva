package com.abada.eva.test.property;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author david
 */

public class Property {

    private Properties property;

    public Property() throws IOException {


        //Load Properties File
        property = new Properties();
        InputStream fileproperty;
        try {
            fileproperty = new FileInputStream("mesasgeProperties");
        } catch (FileNotFoundException ex) {
            fileproperty = this.getClass().getResourceAsStream("/com/abada/eva/test/properties/message.properties");
        }
        property.load(fileproperty);

    }

    /**
     * Returns property
     *
     * @return
     */
    public Properties getProperty() {
        return property;
    }
}