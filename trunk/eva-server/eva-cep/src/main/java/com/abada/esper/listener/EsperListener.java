/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.listener;

import ca.uhn.hl7v2.model.Message;
import com.abada.esper.configuration.model.Statement;
import com.abada.esper.historic.service.HistoricActionService;
import com.abada.eva.api.Action;
import com.abada.eva.api.EsperService;
import com.abada.eva.api.SetEsperService;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * This class is used to listen an event from esper and in that moment call all
 * the action that have to be triggered for this EPL.
 *
 * This class load the actions and the generic updatelistener for the same EPL
 * from a SpringContext.
 *
 * @author katsu
 */
public class EsperListener extends GenericApplicationContext implements UpdateListener {

    /**
     * Bean definition reader for XML bean definitions
     */
    private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);
    /**
     * Spring xml where is all the context configuration
     */
    private InputStreamResource xmlStreamConfig;
    /**
     * Beans that implements Action in the spring context
     */
    private Map<String, Action> actions;
    /**
     * Beans that implements UpdateListener in the spring context
     */
    private Map<String, UpdateListener> esperListeners;
    /**
     * Statement to execute
     */
    private Statement statement;
    /**
     * Service to register executed events
     */
    private HistoricActionService historicActionService;   

    public EsperListener(HistoricActionService historicActionService, InputStream is, Statement s, EsperService esperService) {
        super();

        xmlStreamConfig = new InputStreamResource(is);

        reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);

        load(xmlStreamConfig);
        refresh();
        
        //Find Action
        this.actions = this.getBeansOfType(Action.class);
        this.esperListeners = this.getBeansOfType(UpdateListener.class);

        //Set EsperService in every action that need it
        setEsperServiceInBeans(esperService);
        
        this.statement = s;

        this.historicActionService = historicActionService;        
        
        
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {

        try {
            this.historicActionService.registerActionInput(this.statement.getName(), this.statement.getEPL(), create(newEvents), create(oldEvents), new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Error registering event.", e);
            }
        }

        //First call the generic listeners UpdateListener
        if (esperListeners != null && !esperListeners.isEmpty()) {
            for (UpdateListener ul : esperListeners.values()) {
                try {
                    ul.update(newEvents, oldEvents);
                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Error updating listener.", e);
                    }
                }
            }
        }
        //Call the actions
        if (actions != null && !actions.isEmpty()) {
            Message[] old = create(oldEvents);
            Message[] newe = create(newEvents);

            for (Action a : actions.values()) {
                try {
                    a.doIt(old, newe);
                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Error executing action.", e);
                    }
                }
            }
        }
    }

    private Message[] create(EventBean[] events) {
        if (events == null) {
            return null;
        }
        Object aux;
        List<Message> result = new ArrayList<Message>();
        for (int i = 0; i < events.length; i++) {
            aux = events[i].getUnderlying();
            result.addAll(create(aux));
        }
        return result.toArray(new Message[0]);
    }

    private List<Message> create(Object obj) {
        List<Message> result = new ArrayList<Message>();
        if (obj instanceof Message) {
            result.add((Message) obj);
        }
        if (obj instanceof HashMap) {
            for (Object a : ((HashMap) obj).values()) {
                result.addAll(create(a));
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("The event " + obj + "is not an instance of Message, so the actions could fail.");
            }
        }
        return result;
    }

    /**
     * Load bean definitions from the given XML resources.
     *
     * @param resources one or more resources to load from
     */
    private void load(Resource... resources) {
        this.reader.loadBeanDefinitions(resources);
    }

    private void setEsperServiceInBeans(EsperService esperService) {
        Map<String, SetEsperService> beans=this.getBeansOfType(SetEsperService.class);
        if (beans!=null){
            for (SetEsperService ses:beans.values()){
                ses.setEsperService(esperService);
            }
        }
        
    }
}
