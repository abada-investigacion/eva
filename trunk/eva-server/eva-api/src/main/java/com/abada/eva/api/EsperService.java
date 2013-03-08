/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.api;

import ca.uhn.hl7v2.model.Message;

/**
 *
 * @author katsu
 */
public interface EsperService {
    public void recover() throws Exception;

    /**
     * Return true if core accept messages
     *
     * @return
     */
    public boolean canSend();

    /**
     * Send Message HL7 to Esper core
     *
     * @param message
     */
    public void send(Message message);
    /**
     * Send Generic object to Esper core
     *
     * @param obj
     */
    public void send(Object obj);

    public void finalice();
}
