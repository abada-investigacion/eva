/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.hl7.service;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.GenericParser;
import java.io.*;

/**
 *
 * @author jesus
 */
public class HL7Service {
    
    public Message buildMessage(String msg) throws Exception{
        GenericParser p = new GenericParser();
        p.setXMLParserAsPrimary();
        
        return p.parse(msg);

    }
    
    /*public void prueba() throws HL7Exception, IOException, ClassNotFoundException {

        String msg = "MSH|^~\\&|HIS|System|Hosp|HL7 Genie|20071016055244||ACK^A01|A234242|P|2.5|\r\n" + "MSA|AA|234242|Message Received Successfully|";
        
        String xml = "<ACK xmlns=\"urn:hl7-org:v2xml\"><MSH><MSH.1>|</MSH.1>"+
        "<MSH.2>^~\\&amp;</MSH.2>"+
        "<MSH.3>"+
        "    <HD.1>HIS</HD.1>"+
        "</MSH.3>"+
        "<MSH.4>"+
        "    <HD.1>System</HD.1>"+
        "</MSH.4>"+
        "<MSH.5>"+
        "    <HD.1>Hosp</HD.1>"+
        "</MSH.5>"+
        "<MSH.6>"+
        "    <HD.1>HL7 Genie</HD.1>"+
        "</MSH.6>"+
        "<MSH.7>"+
        "    <TS.1>20071016055244</TS.1>"+
        "</MSH.7>"+
        "<MSH.9>"+
        "    <MSG.1>ACK</MSG.1>"+
        "    <MSG.2>A01</MSG.2>"+
        "</MSH.9>"+
        "<MSH.10>A234242</MSH.10>"+
        "<MSH.11>"+
        "    <PT.1>P</PT.1>"+
        "</MSH.11>"+
        "<MSH.12>"+
        "    <VID.1>2.5</VID.1>"+
        "</MSH.12>"+
    "</MSH>"+
    "<MSA>"+
    "    <MSA.1>AA</MSA.1>"+
    "    <MSA.2>234242</MSA.2>"+
    "    <MSA.3>Message Received Successfully</MSA.3>"+
    "</MSA>"+
"</ACK>";
        
        GenericParser p = new GenericParser();
        p.setXMLParserAsPrimary();
        //EncodingDetector.is
        Message m = p.parse(xml);
       
        System.out.println(m);
        m = p.parse(msg);
        p.setPipeParserAsPrimary();
        System.out.println(m);
        //System.out.println("Esto es la salida: -----"+m);
        DefaultXMLParser p2 = new DefaultXMLParser();
        System.out.println(p2.encode(m));
        
        
        System.out.println(this.serialize(m));
        
        System.out.println(this.unserialize(this.serialize(m)));
    }*/

    private byte[] serialize(Object o) throws IOException {

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(o);  // this es de tipo DatoUdp
        os.close();
        return bs.toByteArray(); // devuelve byte[]

    }

    private Object unserialize(byte[] b) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bs = new ByteArrayInputStream(b); // bytes es el byte[]
        ObjectInputStream is = new ObjectInputStream(bs);
        Object obj =  is.readObject();
        is.close();
        
        return obj;
        
    }
}
