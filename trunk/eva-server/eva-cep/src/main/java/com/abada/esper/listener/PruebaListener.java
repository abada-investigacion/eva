/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 *
 * @author mmartin
 */
public class PruebaListener implements UpdateListener {

    public void update(EventBean[] ebs, EventBean[] ebs1) {

        if (ebs != null) {
            System.out.println("Eventos nuevos:");
            for(int i=0 ; i<ebs.length ; i++){
                System.out.println(ebs[i].get("n"));
                System.out.println("-------------------------------\n\n\n");
            }
            
        } 
        if (ebs1 != null) {
            System.out.println("Eventos viejos");
            for(int i=0 ; i<ebs1.length ; i++){
                System.out.println(ebs1[i].get("n"));
                System.out.println("-------------------------------\n\n\n");
            }
        }
        System.out.println("-------------------------------\n\n\n");
    }
}
