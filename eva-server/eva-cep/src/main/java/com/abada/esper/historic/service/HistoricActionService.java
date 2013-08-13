package com.abada.esper.historic.service;

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
import com.abada.esper.historic.dao.HistoricActionDao;
import com.abada.esper.historic.entities.HistoricAction;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author mmartin
 */
public class HistoricActionService {

    private static final Log logger = LogFactory.getLog(HistoricActionService.class);
    @Resource(name = "historicactiondao")
    private HistoricActionDao dao;
    private boolean historic = true;

    public boolean isHistoric() {
        return historic;
    }

    public void setHistoric(boolean historic) {
        this.historic = historic;
    }

    @Async(value = "historicExecutor")
    public void registerActionInput(String EPLName, String EPL, Message[] newEvents, Message[] oldEvents, Date run) {
        if (historic) {
            logger.trace("Run register action input");
            HistoricAction historicAction = new HistoricAction(UUID.randomUUID().toString(), EPLName, EPL, newEvents, oldEvents, run);
            logger.trace("Persisting Action");
            dao.persistActionInput(historicAction);
        }
    }
}
