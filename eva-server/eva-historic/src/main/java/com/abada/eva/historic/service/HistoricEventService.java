/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.service;

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
import com.abada.eva.historic.dao.HistoricDao;
import com.abada.eva.historic.entities.HistoricEvent;
import com.abada.eva.historic.entities.HistoricGenericEvent;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author mmartin
 */
public class HistoricEventService {

    private static final Log logger = LogFactory.getLog(HistoricEventService.class);
    @Resource(name = "historicdao")
    private HistoricDao dao;
    private boolean historic = true;

    public boolean isHistoric() {
        return historic;
    }

    public void setHistoric(boolean historic) {
        this.historic = historic;
    }

    @Async(value = "historicExecutor")
    public void registerInputMessage(Message event, String principal, Long run) {
        if (historic) {
            logger.trace("Run register input");
            HistoricEvent historicEvent = new HistoricEvent(UUID.randomUUID().toString(), event, run, principal);
            logger.trace("Persisting Event by " + principal);
            dao.persistHistoricEvent(historicEvent);
        }
    }

    @Async(value = "historicExecutor")
    public void registerInputObject(Object event, String principal, Long run) {
        if (historic) {
            logger.trace("Run register input");
            HistoricGenericEvent historicEvent = new HistoricGenericEvent(UUID.randomUUID().toString(), null, event.getClass().getName(), run, principal);
            logger.trace("Persisting Event by " + principal);
            dao.persistHistoricGenericEvent(historicEvent, event);
        }
    }
}
