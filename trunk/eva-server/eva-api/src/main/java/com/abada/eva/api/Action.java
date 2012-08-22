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
public interface Action {
    void doIt(Message [] oldMessages,Message [] newMessages);
}
