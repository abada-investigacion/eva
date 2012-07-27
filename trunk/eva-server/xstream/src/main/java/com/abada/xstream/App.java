package com.abada.xstream;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Prueba p = new Prueba("dddd", new Manolo("juanito"), "ggggg");
       
        ArrayList<Manolo> al = new ArrayList<Manolo>();
        
        al.add(new Manolo("juanito"));
        al.add(new Manolo("pepito"));
        
        p.setManolos(al);
        
        XStream x = new XStream();
        x.autodetectAnnotations(true);
        //x.processAnnotations(p.getClass());
        System.out.println(x.toXML(p));
        
    }
}
