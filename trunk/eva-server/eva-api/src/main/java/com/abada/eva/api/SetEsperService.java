/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.api;

/**
 * If an updatelistener or and Action want EsperService as parameter has to implement
 * this interface
 * @author katsu
 */
public interface SetEsperService {
    public void setEsperService(EsperService esperService);
    public EsperService getEsperService();
    
}
