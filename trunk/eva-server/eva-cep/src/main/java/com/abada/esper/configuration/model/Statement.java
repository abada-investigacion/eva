/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.configuration.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *  Class for marshall and unmarshall the xml configuration file for EVA
 * @author mmartin
 */
public class Statement {
    @XStreamAsAttribute
    private String name;
    //@XStreamImplicit(itemFieldName="listener")
    //private List<String> listeners;
    private String EPL;
    private String springContext;
    //@XStreamImplicit(itemFieldName="subscriber")
    //private List<String> subscribers;

    public String getEPL() {
        return EPL;
    }

    public void setEPL(String EPL) {
        this.EPL = EPL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpringContext() {
        return springContext;
    }

    public void setSpringContext(String springContext) {
        this.springContext = springContext;
    }

}
