/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest.eventaction;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author david
 */
@Controller
public class EventActionController {

    @Resource(name = "historicactiondao")
    private HistoricActionDao historicActionDao ;
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
            lHistoricAction =(List<HistoricAction>) this.historicActionDao.getAll(grequest);
            for (HistoricAction h:lHistoricAction){
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
    
  
}
