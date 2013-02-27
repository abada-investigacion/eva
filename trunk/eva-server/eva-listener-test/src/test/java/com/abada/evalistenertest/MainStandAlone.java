/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import ca.uhn.hl7v2.model.v25.message.ADT_A05;
import ca.uhn.hl7v2.model.v25.segment.AL1;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.PV1;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import com.abada.eva.test.listener.PruebaListener;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mmartin
 */
public class MainStandAlone {
    
    public static void main(String args[]) throws Exception{
        
        System.out.println("\n\n\n EMPEZAMOSSSSSSSSS");
        Configuration configuration = new Configuration();
        configuration.addEventType("EVENTA", EventoA.class.getName());
        configuration.addEventType("EVENTB", EventoB.class.getName());
        
        EventoA a = new EventoA(122, "1", "cagao");
        EventoA b = new EventoA(122, "2", "cagao");
        EventoB c = new EventoB(122, "3", "cagarro");
        EventoB d = new EventoB(122, "4", "cagarro2");
        
        
        EPServiceProvider epService = EPServiceProviderManager.getProvider("sample", configuration);
        
        EPStatement statement = epService.getEPAdministrator().createEPL(getEPL());
        
        
        
        statement.addListener(new Listener());
        
        epService.getEPRuntime().sendEvent(a);
        epService.getEPRuntime().sendEvent(b);
        epService.getEPRuntime().sendEvent(c);
        //epService.getEPRuntime().sendEvent(b);
        epService.getEPRuntime().sendEvent(d);
        epService.getEPRuntime().sendEvent(d);
        epService.getEPRuntime().sendEvent(d);
        epService.getEPRuntime().sendEvent(d);
        epService.getEPRuntime().sendEvent(d);
        
        Thread.currentThread().sleep(5000);
        
    
            
        System.out.println("\n\n\n FINNNN");
    }

    private static String getEPL() throws Exception {
        return FileUtils.readFileToString(new File("/home/mmartin/NetBeansProjects/eva/trunk/eva-server/eva-listener-test/src/main/resources/epl.txt"));
    }
    
    public static ADT_A05 getMessageADT_A31() throws Exception {

        ADT_A05 adt = new ADT_A05();

        // Populate the MSH Segment          
        MSH mshSegment = adt.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        mshSegment.getMessageType().getMessageCode().setValue("ADT");
        mshSegment.getMessageType().getTriggerEvent().setValue("A31");
        mshSegment.getMessageType().getMessageStructure().setValue("ADT_A05");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");
        // Populate the PID Segment
        PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");


        pid.getPatientIdentifierList(0).getCx1_IDNumber().setValue(Integer.toString(1));
        pid.getPatientIdentifierList(0).getCx5_IdentifierTypeCode().setValue("PI");

        PV1 pv1 = adt.getPV1();
        pv1.getReferringDoctor(0).getGivenName().setValue("Dr. who");


        AL1 al1 = adt.getAL1();
        al1.getAllergySeverityCode().getAlternateIdentifier().setValue("SV");
        al1.getAl12_AllergenTypeCode().getCe2_Text().setValue("FA");
        al1.getAllergenCodeMnemonicDescription().getCe2_Text().setValue("HAM");
 

        //

//         CX[] cxarray = pid.getPid3_PatientIdentifierList();
//        if (cxarray != null && cxarray.length > 0) {
//            for (CX cxx : cxarray) {
//                addPatientId(patient, cxx.getCx1_IDNumber().getValue(), cxx.getCx5_IdentifierTypeCode().getValue());
//            }
//        }

        //pid.getPatientIdentifierList(0).get().setValue("123456")
        Parser p = new DefaultXMLParser();


        return adt;
    }
}
