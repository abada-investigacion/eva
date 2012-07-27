/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author jesus
 */
@XStreamAlias("manolete")
public class Manolo {
    private String name;

    public Manolo(String name) {
        this.name = name;
    }
    
    
}
