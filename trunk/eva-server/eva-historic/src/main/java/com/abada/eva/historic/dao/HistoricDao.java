/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao;

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
