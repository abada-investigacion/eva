/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao;

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

import com.abada.eva.historic.entities.HistoricEvent;
import com.abada.eva.historic.entities.HistoricGenericEvent;
import com.abada.eva.historic.entities.HistoricGenericEventContainer;
import java.util.List;

/**
 *
 * @author mmartin
 */
public interface HistoricDao {
    /**
     * Persist an {@link HistoricEvent}
     * @param h
     * @return 
     */
    public HistoricEvent persistHistoricEvent(HistoricEvent h);    
    /**
     * Return the number of historic events entries
     * @return 
     */
    public Long getHistoricEventCount();
    /**
     * Return a list of HistoricEvents in a range.
     * @param index
     * @param max
     * @return 
     */
    public List<HistoricEvent> getHistoricEvents(long index,long max);
    
    /**
     * Persist an {@link HistoricGenericEvent}
     * @param h
     * @return 
     */
    public HistoricGenericEvent persistHistoricGenericEvent(HistoricGenericEvent h,Object obj);    
    /**
     * Return the number of historic events entries
     * @return 
     */
    public Long getHistoricGenericEventCount();
    /**
     * Return a list of HistoricGenericEvent in a range.
     * @param index
     * @param max
     * @return 
     */
    public List<HistoricGenericEventContainer> getHistoricGenericEvent(long index,long max) throws Exception;
}
