/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao;

import com.abada.eva.historic.entities.HistoricEvent;

/**
 *
 * @author mmartin
 */
public interface HistoricDao {
    
    
    public HistoricEvent persistHistoricEvent(HistoricEvent h);
    
    
}
