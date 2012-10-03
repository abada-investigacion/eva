/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evawebeventactionmodule;

import com.abada.esper.historic.entities.HistoricAction;
import com.abada.extjs.ExtjsStore;
import com.abada.extjs.SearchField;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.abada.gson.GsonImpl;
import com.abada.springframework.web.client.RestTemplate;
import com.abada.springframework.web.servlet.menu.MenuEntry;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author david
 */
@Controller
public class EventactionController {

    @Autowired
    private URL serverUrl;
    @Resource
    private ApplicationContext context;

    @RequestMapping(value = "/event/epl.htm")
    @MenuEntry(icon = "", menuGroup = "Epl", order = 0, text = "Epl")
    public String get(Model model) {
        model.addAttribute("js", Arrays.asList("eventaction/js/eventaction.js", "eventaction/js/common/gridEventaction.js"));
        return "dynamic/main";
    }

    /**
     * load dms grid
     *
     * @param request
     * @param start
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/event/historicepl.do", method = RequestMethod.GET)
    public @ResponseBody
    String gridloadepl(HttpServletRequest request) throws Exception {
        Map<String, Object> map = com.abada.web.util.URL.parseRequest(request);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        restTemplate.setRequestFactory(request);
        //String result = (String) restTemplate.getForObject(new URL(serverUrl, "rs/event/search/epl" + com.abada.web.util.URL.paramUrlGrid(map)).toURI(), String.class);
       
        
         Type stringType = new TypeToken<String>() {
        }.getType();
        String result = (String) restTemplate.getForObjectJson(new URL(serverUrl, "rs/event/search/epl").toString() + com.abada.web.util.URL.paramUrlGrid(map), stringType);
       return result;

    }
}
