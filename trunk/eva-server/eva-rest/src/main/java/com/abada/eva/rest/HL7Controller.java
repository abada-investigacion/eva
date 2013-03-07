/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import com.abada.esper.service.EsperService;
import com.abada.eva.historic.service.HistoricEventService;
import com.abada.eva.hl7.service.HL7Service;
import com.abada.json.Json;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jesus
 */
@Controller
public class HL7Controller {

    private static final String LOCKED_MSG = "Service temporal Unavalaible, in recovery mode.";
    private static final Log logger = LogFactory.getLog(HL7Controller.class);
    @Resource(name = "historicEventService")
    private HistoricEventService heservice;
    @Resource(name = "hl7Service")
    private HL7Service hl7service;
    @Resource(name = "esperService")
    private EsperService cep;
    @Resource(name = "jsonSerializer")
    private Json serializer;

    /**
     * REST Service wher we receive the HL7 Message
     *
     * @param hl7
     * @param request
     * @param mav
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/rs/sendmessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String send(String hl7, HttpServletRequest request, Model mav) throws Exception {
        //if (logger.isDebugEnabled()) logger.debug(hl7);
        Message msg = null;
        String ack = null;

        try {
            msg = hl7service.buildMessage(hl7);
        } catch (HL7Exception e) {
            ack = hl7service.createAckComunicationErrorAsString(msg, e);
        }

        if (ack == null) {
            if (cep.canSend()) {

                heservice.registerInputMessage(msg, request.getUserPrincipal().getName(), System.currentTimeMillis());
                cep.send(msg);
                ack = hl7service.createAckComunicationRejectAsString(msg, LOCKED_MSG);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Restoring so no accepted message");
                }
                ack = hl7service.createAckComunicationPositiveAsString(msg);
            }
        }
        mav.addAttribute("text", ack);
        return "plain";
    }

    @RequestMapping(value = "/rs/send", method = {RequestMethod.GET, RequestMethod.POST})
    public void sendObject(String object, String type, HttpServletRequest request) throws Exception {
        if (cep.canSend()) {
            try {
                Class clazz = this.getClass().getClassLoader().loadClass(type);

                Object obj = serializer.deserialize(object, clazz);
                heservice.registerInputObject(obj, request.getUserPrincipal().getName(), System.currentTimeMillis());
                if (obj != null) {                    
                    cep.send(obj);
                } else {
                    if (logger.isErrorEnabled()) {
                        logger.error("Couldn't deserialize object");
                    }
                }
            } catch (ClassNotFoundException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("Class not found " + type);
                }
                throw e;
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Restoring so no accepted objects");
            }
        }
    }

    @RequestMapping(value = "/rs/recover", method = {RequestMethod.POST})
    public void recover(HttpServletRequest request) throws Exception {
        cep.recover();
    }

    public HistoricEventService getHeservice() {
        return heservice;
    }

    public void setHeservice(HistoricEventService heservice) {
        this.heservice = heservice;
    }

    public HL7Service getHl7service() {
        return hl7service;
    }

    public void setHl7service(HL7Service hl7service) {
        this.hl7service = hl7service;
    }
}
