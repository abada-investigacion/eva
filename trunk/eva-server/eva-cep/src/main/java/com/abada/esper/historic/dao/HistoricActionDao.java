/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.dao;

import com.abada.esper.historic.entities.HistoricAction;

/**
 *
 * @author mmartin
 */
public interface HistoricActionDao {
    
    /**
     * Persist an {@link HistoricAction}
     * @param h
     * @return 
     */
    public void persistActionInput(HistoricAction h);
    
}
