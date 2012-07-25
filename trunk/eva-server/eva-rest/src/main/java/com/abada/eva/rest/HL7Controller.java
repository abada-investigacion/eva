/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.rest;

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
    
    
    
    @RequestMapping(value = "/rs/sendmessage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView send(String hl7, HttpServletRequest request){
        
        @Resource(name = "historicEventService")
        private HistoricEventService heservice;
        
        ModelAndView mav = new ModelAndView("plain");
        mav.addObject("text", "holamundo");
        return mav;
    }
    
    
}
