/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.api;

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

/**
 * All the actions that trigger Eva must implements this interface.
 * Example of actions could be:
 *  * Send SMS, Mails, or other thing
 *  * Show an alert in any application
 *  * ...
 * @author katsu
 */
public interface Action {
    /**
     * When the EPL activate, then this action is called.
     * 
     * Like in the UpdateListener in Esper, the oldMessages contains the las messages that
     * match with the EPL or null if there nothing. The new Messages contains the current messages that
     * match the EPL. 
     * 
     * The oldMessages and the newMessages could be null, if the EPL don't recover it. For more details see
     * the Esper documentation.
     * @param oldMessages 
     * @param newMessages 
     */
    void doIt(Message [] oldMessages,Message [] newMessages);
}
