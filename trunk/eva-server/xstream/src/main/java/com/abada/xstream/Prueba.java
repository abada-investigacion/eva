/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.List;

/**
 *
 * @author jesus
 */
@XStreamAlias("Pruebecita")
public class Prueba {
    @XStreamAsAttribute
    private String cagada;
    //private Manolo manolo;
    @XStreamOmitField
    private String capullon;
    @XStreamImplicit(itemFieldName="juanitos")
    private List<Manolo> manolos;

    public String getCagada() {
        return cagada;
    }

    public void setCagada(String cagada) {
        this.cagada = cagada;
    }

    public String getCapullon() {
        return capullon;
    }

    public void setCapullon(String capullon) {
        this.capullon = capullon;
    }

    /*public Manolo getManolo() {
        return manolo;
    }

    public void setManolo(Manolo manolo) {
        this.manolo = manolo;
    }*/

    public Prueba(String cagada, Manolo manolo, String capullon) {
        this.cagada = cagada;
        //this.manolo = manolo;
        this.capullon = capullon;
    }

    public List<Manolo> getManolos() {
        return manolos;
    }

    public void setManolos(List<Manolo> manolos) {
        this.manolos = manolos;
    }
    
    
    
    
}
