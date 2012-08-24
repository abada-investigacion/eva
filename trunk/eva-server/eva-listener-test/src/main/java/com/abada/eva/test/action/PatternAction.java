/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.test.action;

import ca.uhn.hl7v2.model.Message;
import com.abada.eva.api.Action;

/**
 *
 * @author mmartin
 */
public class PatternAction implements Action{
    
     int count;

    public void doIt(Message[] oldMessages, Message[] newMessages) {
      System.out.println("********** Evento recibido **********");
        if (newMessages != null) {
            count = newMessages.length -1;
//            for (Message e : newMessages) {
                System.out.println("evento nuevo: " + newMessages[count].getMessage());
               // System.out.println("size: " + ebs[count].get("size"));
//                count++;
//            }
        }
        if (oldMessages != null) {
            count = oldMessages.length -1;
//            for (Message e : oldMessages) {
                System.out.println("evento viejo: " + oldMessages[count].getMessage());
              //  System.out.println("size: " + ebs[count].get("size"));
//                count++;
//            }
        }
    }
    
}
