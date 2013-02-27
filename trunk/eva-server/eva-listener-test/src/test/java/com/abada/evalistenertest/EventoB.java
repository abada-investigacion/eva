/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

/**
 *
 * @author mmartin
 */
public class EventoB {
   
    private int id;
    private String code;
    private String value;

    public EventoB(int id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
