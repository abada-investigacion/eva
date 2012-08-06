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
public class PruebaListener implements UpdateListener{

    public void update(EventBean[] ebs, EventBean[] ebs1) {
        
        System.out.println("Macho yooooooo!!!!!");
        ADT_A01 adt = (ADT_A01)ebs[0].get("msg");
        System.out.println(adt.getMSH().getMsh10_MessageControlID().getValue() );
        System.out.println("Macho los demas!!!");
    }
    
}
