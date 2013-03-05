/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.web.historic.entities;

/**
 *
 * @author david
 */
public class Messagexml {
    private String message;
    private String xml;
    private boolean newevent;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public boolean isNewevent() {
        return newevent;
    }

    public void setNewevent(boolean newevent) {
        this.newevent = newevent;
    }
    
}
