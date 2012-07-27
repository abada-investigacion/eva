/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import com.abada.eva.historic.service.HistoricEventService;
import com.abada.eva.hl7.service.HL7Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jesus
 */
@Controller
public class HL7Controller {
    
    @Resource(name = "historicEventService")
    private HistoricEventService heservice;
    @Resource(name = "hl7Service")
    private HL7Service hl7service;
    
    @RequestMapping(value = "/rs/sendmessage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView send(String hl7, HttpServletRequest request) throws Exception{
        Message msg = null;
        try{
            msg = hl7service.buildMessage(hl7);
        }catch(HL7Exception e){
            return null;
        }
        
        heservice.registerInput(msg, request.getUserPrincipal().getName(), System.currentTimeMillis());
        
        String ack = hl7service.createAckComunicationPositiveAsString(msg);
        ModelAndView mav = new ModelAndView("plain");
        mav.addObject("text", ack);
        return mav;
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
