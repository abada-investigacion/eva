/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.test.action;

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
