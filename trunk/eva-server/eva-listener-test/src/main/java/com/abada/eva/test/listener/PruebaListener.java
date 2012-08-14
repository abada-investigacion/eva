/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.test.listener;

import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 *
 * @author mmartin
 */
public class PruebaListener implements UpdateListener {

    int count;

    public void update(EventBean[] ebs, EventBean[] ebs1) {

        System.out.println("Evento recibido");
        if (ebs != null) {
            count = 0;
            for (EventBean e : ebs) {
                System.out.println("evento nuevo: " + ebs[count].get("n"));
               // System.out.println("size: " + ebs[count].get("size"));
                count++;
            }
        }
        if (ebs1 != null) {
            count = 0;
            for (EventBean e : ebs) {
                System.out.println("evento viejo: " + ebs[count].get("n"));
              //  System.out.println("size: " + ebs[count].get("size"));
                count++;
            }
        }



    }
}
