/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.test.action;

import ca.uhn.hl7v2.model.Message;
import com.abada.eva.api.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mmartin
 */
public class PatternAction implements Action {

    private static final Log logger = LogFactory.getLog(PatternAction.class);
    int count;

    public void doIt(Message[] oldMessages, Message[] newMessages) {
        if (logger.isDebugEnabled()) logger.debug("********** Evento recibido **********"+ (oldMessages==null?"":oldMessages.length)+" "+(newMessages==null?"":newMessages.length));
        if (newMessages != null) {
            count = newMessages.length - 1;
//            for (Message e : newMessages) {
            if (logger.isDebugEnabled()) logger.debug("evento nuevo: " + newMessages[count].getMessage());
            // System.out.println("size: " + ebs[count].get("size"));
//                count++;
//            }
        }
        if (oldMessages != null) {
            count = oldMessages.length - 1;
//            for (Message e : oldMessages) {
            if (logger.isDebugEnabled()) logger.debug("evento viejo: " + oldMessages[count].getMessage());
            //  System.out.println("size: " + ebs[count].get("size"));
//                count++;
//            }
        }
    }
}
