/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

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
                System.out.println("       "+((EventoA)e.get("a")).getCode() + " " + ((EventoB)e.get("b")).getIdmsg());
                  
            }
        }
        if (ebs1 != null) {
        System.out.println("ventos viejos");    
            for (EventBean e : ebs1) {
                System.out.println("       "+((EventoA)e.get("a")).getCode() + " " + ((EventoB)e.get("b")).getIdmsg());
            }
        }



    }
}
