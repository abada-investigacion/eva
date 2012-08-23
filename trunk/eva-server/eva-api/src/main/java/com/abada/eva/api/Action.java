/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.api;

import ca.uhn.hl7v2.model.Message;

/**
 * All the actions that trigger Eva must implements this interface.
 * Example of actions could be:
 *  * Send SMS, Mails, or other thing
 *  * Show an alert in any application
 *  * ...
 * @author katsu
 */
public interface Action {
    /**
     * When the EPL activate, then this action is called.
     * 
     * Like in the UpdateListener in Esper, the oldMessages contains the las messages that
     * match with the EPL or null if there nothing. The new Messages contains the current messages that
     * match the EPL. 
     * 
     * The oldMessages and the newMessages could be null, if the EPL don't recover it. For more details see
     * the Esper documentation.
     * @param oldMessages 
     * @param newMessages 
     */
    void doIt(Message [] oldMessages,Message [] newMessages);
}
