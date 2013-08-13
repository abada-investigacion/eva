/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
    
    public Listener(int count){
        this.count = count;
    }

    public void update(EventBean[] ebs, EventBean[] ebs1) {

    //    System.out.println("Evento recibido");
        if (ebs != null) {
    //       System.out.println("eventos nuevos: "); 
            for (EventBean e : ebs) {
                CDABean c = (CDABean)e.get("c");
                System.out.println("   Eventos nuevos "+count+": ("+c.getIdentificador() + " - " +c.getCda() +") - " + ((ORU_R01)e.get("oru")).getMSH().getMsh10_MessageControlID().getValue());
                //ebs[0].
            }
        }
       if (ebs1 != null) {
        //System.out.println("eventos viejos");    
            for (EventBean e : ebs1) {
                CDABean c = (CDABean)e.get("c");
                System.out.println("   Eventos viejos "+count+": ("+c.getIdentificador() + " - " +c.getCda() +") - " + ((ORU_R01)e.get("oru")).getMSH().getMsh10_MessageControlID().getValue());
            }
        }


       System.out.println("\n\n");
    }
}
