/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

import ca.uhn.hl7v2.model.Message;
import com.abada.eva.api.Action;

/**
 *
 * @author katsu
 */
public abstract class AbstractDimexAction<T> extends AbstractDimex<T> implements Action {
    public AbstractDimexAction() {
        super();
    }
    
    public void doIt(Message[] oldMessages, Message[] newMessages) {
        doItPriv(oldMessages, newMessages);
    }

}
