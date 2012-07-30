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
public class PruebaListener implements UpdateListener{

    public void update(EventBean[] ebs, EventBean[] ebs1) {
        
        System.out.println("Macho yooooooo!!!!!");
        System.out.println(ebs[0].get("n"));
        System.out.println("Macho los demas!!!");
    }
    
}
