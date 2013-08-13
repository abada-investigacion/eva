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


import com.abada.esper.configuration.model.*;
import com.abada.esper.service.EsperServiceImpl;
import com.espertech.esper.client.*;
import com.espertech.esper.client.scopetest.SupportUpdateListener;
import com.thoughtworks.xstream.XStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;


/**
 * Hello world!
 *
 */
public class App {
    
   
    public static void main(String[] args) throws Exception {
//        Prueba p = new Prueba("dddd", new Manolo("juanito"), "ggggg");
//       
//        ArrayList<Manolo> al = new ArrayList<Manolo>();
//        
//        al.add(new Manolo("juanito"));
//        al.add(new Manolo("pepito"));
//        
//        p.setManolos(al);
//        
//        XStream x = new XStream();
//        x.autodetectAnnotations(true);
//        //x.processAnnotations(p.getClass());
//        System.out.println(x.toXML(p));


        
        
      /*  EPAdministrator admin = epService.getLoader().getEPAdministrator();

        List<EPStatement> l = new ArrayList<EPStatement>();

        EPStatement a = admin.createEPL("select count(*) from PEX_P07");

        UpdateListener listener = new SupportUpdateListener();

        a.addListener(listener);

        l.add(a);*/

      /* Statements s=new Statements();
       
       List<Statement> lStatements= new ArrayList<Statement>();
       List<String> ls= new ArrayList();
        
       ls.add("com.abada.pollas.cagao");
       Statement st=new Statement();
       
       st.setListeners(ls);
       
       ls= new ArrayList();
       ls.add("com.abada.pollas.cagao2");
       
       st.setSubscribers(ls);
       
       st.setName("laleche");
       st.setEPL("Select * from mipolla");
       
       lStatements.add(st);
       
       s.setStatements(lStatements);
       
        XStream x = new XStream();
        x.processAnnotations(Statements.class);
        //   x.autodetectAnnotations(true);
        //x.processAnnotations(p.getClass());
        System.out.println(x.toXML(s));*/
        System.out.println(((UpdateListener)Class.forName("com.abada.esper.listener.PruebaListener").newInstance()).getClass().getName());
    }
}
