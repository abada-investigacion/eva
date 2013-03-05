/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.dao;

import com.abada.esper.historic.entities.HistoricAction;
import com.abada.springframework.web.servlet.command.extjs.gridpanel.GridRequest;
import java.util.List;

/**
 *
 * @author mmartin
 */
public interface HistoricActionDao {

    /**
     * Persist an {@link HistoricAction}
     *
     * @param h
     * @return
     */
    public void persistActionInput(HistoricAction h);

    public List<HistoricAction> getAll(GridRequest grequest) throws Exception;

    public Long loadSizeAll(GridRequest grequest) throws Exception;

    public List<HistoricAction> getid(String id);
}
