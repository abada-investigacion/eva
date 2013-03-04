/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import es.sacyl.eva.beans.CDABean;

/**
 *
 * @author mmartin
 */
public class Listener implements UpdateListener {

    int count;

    public void update(EventBean[] ebs, EventBean[] ebs1) {

        System.out.println("Evento recibido");
        if (ebs != null) {
           System.out.println("eventos nuevos: "); 
            for (EventBean e : ebs) {
                System.out.println("       "+((CDABean)e.get("c")).getIdentificador() + " " + ((ORU_R01)e.get("oru")).getMSH().getMsh10_MessageControlID().getValue());
                  
            }
        }
        if (ebs1 != null) {
        System.out.println("ventos viejos");    
            for (EventBean e : ebs1) {
                System.out.println("       "+((CDABean)e.get("c")).getIdentificador() + " " + ((ORU_R01)e.get("oru")).getMSH().getMsh10_MessageControlID().getValue());
            }
        }



    }
}
