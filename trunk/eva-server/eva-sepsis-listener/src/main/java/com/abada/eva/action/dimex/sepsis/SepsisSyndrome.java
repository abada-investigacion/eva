/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import java.util.Map;


/**
 *
 * @author jesus
 */
public class SepsisSyndrome {
    
    private Boolean result;
    private String UUID; 
    private Map<String, Object> properties;
    private String nhc;

    public SepsisSyndrome() {
        /*this.result = result;
        this.properties = properties;
        this.nhc = (String)properties.get(SepsisSyndromeUpdateListener.NHC);*/
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
        this.nhc = (String)properties.get(SepsisSyndromeUpdateListener.NHC);
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getNhc() {
        return nhc;
    }

    public void setNhc(String nhc) {
        this.nhc = nhc;
    }
    

   
    
    
    
}
