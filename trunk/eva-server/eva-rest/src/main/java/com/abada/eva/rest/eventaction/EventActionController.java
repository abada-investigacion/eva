/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest.eventaction;

import com.abada.esper.historic.entities.Messagexml;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import com.abada.esper.historic.dao.HistoricActionDao;
import com.abada.esper.historic.entities.HistoricAction;
import com.abada.extjs.ExtjsStore;
import com.abada.springframework.web.servlet.command.extjs.gridpanel.GridRequest;
import com.abada.springframework.web.servlet.command.extjs.gridpanel.factory.GridRequestFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author david
 */
@Controller
public class EventActionController {

    @Resource(name = "historicactiondao")
    private HistoricActionDao historicActionDao;
    private static final Log logger = LogFactory.getLog(EventActionController.class);

    /**
     * Search a list of History Action by params
     *
     * @param filter Filter conditions of results. Set in JSON by an array of
     * FilterRequestPriv
     * @param sort Order of results. Set in JSON by an array of OrderByRequest
     * @param limit Set the limit of the results. Use it for pagination
     * @param start Set the start of the results. Use it for pagination
     * @return Return a list of results.
     */
    @RequestMapping(value = "/rs/event/search/epl", method = RequestMethod.GET)
    public ExtjsStore getSearch(String filter, String sort, Integer limit, Integer start) {

        List<HistoricAction> lHistoricAction;
        ExtjsStore aux = new ExtjsStore();
        try {
            GridRequest grequest = GridRequestFactory.parse(sort, start, limit, filter);
            lHistoricAction = (List<HistoricAction>) this.historicActionDao.getAll(grequest);
            for (HistoricAction h : lHistoricAction) {
                h.setNewEvents(null);
                h.setOldEvents(null);
            }
            aux.setData(lHistoricAction);
            aux.setTotal(this.historicActionDao.loadSizeAll(grequest).intValue());
        } catch (Exception e) {
            logger.error(e);
        }

        return aux;
    }

    @RequestMapping(value = "rs/event/search/message", method = RequestMethod.GET)
    public ExtjsStore getSearchmessage(String filter, String sort, Integer limit, Integer start) {
        Parser parser = new DefaultXMLParser();
        List<Messagexml> lm = new ArrayList<Messagexml>();
        Messagexml message;
        List<HistoricAction> lHistoricAction;
        ExtjsStore aux = new ExtjsStore();
        int numbernewevent=0,numberoldevent=0;
        try {
            GridRequest grequest = GridRequestFactory.parse(sort, start, limit, filter);
            lHistoricAction = (List<HistoricAction>) this.historicActionDao.getAll(grequest);
            for (HistoricAction h : lHistoricAction) {
                if(h.getNewEvents()!=null){
                    numbernewevent=h.getNewEvents().length;
                for (int i = 0; i < numbernewevent; i++) {
                    message = new Messagexml();
                    message.setMessage(h.getNewEvents()[i].getName());
                    message.setXml(parser.encode(h.getNewEvents()[i]));
                    message.setNewevent(true);
                    lm.add(message);
                }
                }
                if(h.getOldEvents()!=null){
                    numberoldevent=h.getOldEvents().length;
                for (int i = 0; i < h.getOldEvents().length; i++) {
                    message = new Messagexml();
                    message.setMessage(h.getOldEvents()[i].getName());
                    message.setXml(parser.encode(h.getOldEvents()[i]));
                    message.setNewevent(false);
                    lm.add(message);
                }
                }
                aux.setData(lm);
                aux.setTotal(numbernewevent+numberoldevent);
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return aux;
    }

    /**
     * Search a list of History Action by params
     *
     * @param filter Filter conditions of results. Set in JSON by an array of
     * FilterRequestPriv
     * @param sort Order of results. Set in JSON by an array of OrderByRequest
     * @param limit Set the limit of the results. Use it for pagination
     * @param start Set the start of the results. Use it for pagination
     * @return Return a list of results.
     */
    @RequestMapping(value = "/rs/event/search/epl/id/{id}", method = RequestMethod.GET)
    public List<HistoricAction> get(@PathVariable String id) {

        List<HistoricAction> lHistoricAction = null;

        try {

            lHistoricAction = (List<HistoricAction>) this.historicActionDao.getid(id);


        } catch (Exception e) {
            logger.error(e);
        }

        return lHistoricAction;
    }

    @RequestMapping(value = "/rs/event/search/message/id/{id}", method = RequestMethod.GET)
    public List<Message[]> getMessages(@PathVariable String id) {
        List<Message[]> message = new ArrayList();
        List<HistoricAction> lHistoricAction = null;

        try {

            lHistoricAction = (List<HistoricAction>) this.historicActionDao.getid(id);
            if (lHistoricAction.size() == 1) {
                if (lHistoricAction.get(0).getNewEvents() != null) {
                    message.add(lHistoricAction.get(0).getNewEvents());
                }
                if (lHistoricAction.get(0).getOldEvents() != null) {
                    message.add(lHistoricAction.get(0).getOldEvents());
                }
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return message;
    }
}
