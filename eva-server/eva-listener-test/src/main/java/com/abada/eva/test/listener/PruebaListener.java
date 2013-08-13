/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.test.listener;

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
