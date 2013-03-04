/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

/**
 *
 * @author mmartin
 */
public class EventoA {
    
    private int id;
    private String code;
    private String value;
    private String []array;

    public EventoA(int id, String code, String value, String[] array) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.array = array;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
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
