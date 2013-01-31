package com.abada.esper.historic.service;

import ca.uhn.hl7v2.model.Message;
import com.abada.esper.historic.dao.HistoricActionDao;
import com.abada.esper.historic.entities.HistoricAction;
import com.espertech.esper.client.EventBean;
import java.util.Date;
import java.util.List;
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

    @Async(value = "historicExecutor")
    public void registerActionInput(String EPLName, String EPL, Message[] newEvents, Message[] oldEvents, Date run) {
        logger.trace("Run register action input");
        HistoricAction historicAction = new HistoricAction(UUID.randomUUID().toString(), EPLName, EPL, newEvents, oldEvents, run);
        logger.trace("Persisting Action");
        dao.persistActionInput(historicAction);
    }

}