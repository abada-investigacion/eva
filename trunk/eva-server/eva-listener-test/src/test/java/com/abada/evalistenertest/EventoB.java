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
    private int idmsg;
    private String code;
    private String value;
    private String array[];

    public EventoB(int id, String code, String value, int idmsg, String[] array) {
        this.id = id;
        this.idmsg = idmsg;
        this.code = code;
        this.value = value;
        this.array=array;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public int getIdmsg() {
        return idmsg;
    }

    public void setIdmsg(int idmsg) {
        this.idmsg = idmsg;
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
