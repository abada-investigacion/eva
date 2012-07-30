/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.configuration.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author mmartin
 */
public class Statement {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit(itemFieldName="listener")
    private List<String> listeners;
    private String EPL;
    @XStreamImplicit(itemFieldName="subscriber")
    private List<String> subscribers;

    public String getEPL() {
        return EPL;
    }

    public void setEPL(String EPL) {
        this.EPL = EPL;
    }

    public List<String> getListeners() {
        return listeners;
    }

    public void setListeners(List<String> listeners) {
        this.listeners = listeners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<String> subscribers) {
        this.subscribers = subscribers;
    }
    
}
