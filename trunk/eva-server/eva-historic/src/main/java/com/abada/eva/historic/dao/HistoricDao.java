/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao;

import com.abada.eva.historic.entities.HistoricEvent;
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
    public Long getCount();
    /**
     * Return a list of HistoricEvents in a range.
     * @param index
     * @param max
     * @return 
     */
    public List<HistoricEvent> getHistoricEvents(long index,long max);
}
