/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

/**
 *
 * @author jesus
 */
public class ORU_R01Custom {
    
    private String messageId;
    private String nhc;
    private boolean hemocultivo;

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setNhc(String nhc) {
        this.nhc = nhc;
    }

    public void setHemocultivo(boolean hemocultivo) {
        this.hemocultivo = hemocultivo;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getNhc() {
        return nhc;
    }

    public boolean isHemocultivo() {
        return hemocultivo;
    }
    
    
}
