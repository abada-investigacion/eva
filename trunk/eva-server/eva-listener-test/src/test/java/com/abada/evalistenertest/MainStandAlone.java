/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v25.message.ADT_A05;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.model.v25.segment.AL1;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.PV1;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import com.abada.eva.action.dimex.sepsis.SepsisSyndrome;
import com.abada.eva.action.dimex.sepsis.SimpleSepsis;
import com.abada.eva.test.listener.PruebaListener;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.beans.CodificacionBean;
import es.sacyl.eva.beans.DatoBean;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mmartin
 */
public class MainStandAlone {

    public static String path = "/home/jesus/NetBeansProjects/Investigacion";
    //public static String path = "/home/mmartin/NetBeansProjects";
    
    public static void main(String args[]) throws Exception {

        System.out.println("\n\n\n EMPEZAMOSSSSSSSSS");
        Configuration configuration = new Configuration();

        configuration.configure(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/hl7.esper.config.cfg.xml"));
        //configuration.addVariable("vari", String.class, "cagarro", true);



        EPServiceProvider epService = EPServiceProviderManager.getProvider("sample", configuration);

        // COMPROBACION DE SINDROME SEPTICO

        //epService.getEPAdministrator().createEPL(getVariableEPL());        
        EPStatement statement = epService.getEPAdministrator().createEPL(getEPL());

        // COMPROBACION SEPSIS SIMPLE

        //EPStatement statement = epService.getEPAdministrator().createEPL(getEPLSepsisGrave());

        statement.addListener(new Listener());

        epService.getEPRuntime().sendEvent(getCda(1));
       
       epService.getEPRuntime().sendEvent(getORU(1));
       
       Thread.currentThread().sleep(6000);
       
       epService.getEPRuntime().sendEvent(getCda(2));
       
       epService.getEPRuntime().sendEvent(getORU(2));
        
       // epService.getEPRuntime().sendEvent(getSepsisSimple());
        //epService.getEPRuntime().sendEvent(getHemocultivo(false));
        //epService.getEPRuntime().sendEvent(getSepsisSyndrome());
 //Thread.currentThread().sleep(5000);

//pService.getEPRuntime().sendEvent(getORU());
//epService.getEPRuntime().sendEvent(getCda());
        //epService.getEPRuntime().sendEvent(getORU());
        //epService.getEPRuntime().sendEvent(getCda());


        Thread.currentThread().sleep(30000);



        System.out.println("\n\n\n FINNNN");
    }

    private static String getEPL() throws Exception {
        return FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/epl.txt"));
    }

    private static String getEPLSepsisSimple() throws Exception {
        return FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/eplSepsisSimple.txt"));
    }

    private static String getEPLSepsisGrave() throws Exception {
        return FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/eplSepsisGrave.txt"));
    }

    private static String getVariableEPL() throws Exception {
        return FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/variables.epl"));
    }

    public static CDABean getCda(int id) throws Exception {
        CDABean cda = new CDABean(id+"", "ddddd");
        cda.setDni("dff");
        cda.setNhc("63560");
        cda.setNombre("pepito");
        cda.setProcesado(true);
        cda.setTarjeta("MRHR421269917014");
        DatoBean datobean = new DatoBean();
        CodificacionBean cobean = new CodificacionBean("18688-2", "LN");
        datobean.getCodigos().add(cobean);
        datobean.setDato("40");
        datobean.setTitulo("temperatura");
        cda.getDatos().add(datobean);

        datobean = new DatoBean();
        CodificacionBean cobean2 = new CodificacionBean("18708-8", "LN");
        datobean.getCodigos().add(cobean2);
        datobean.setDato("91");
        datobean.setTitulo("frecuencia cardiaca");
        cda.getDatos().add(datobean);

        datobean = new DatoBean();
        CodificacionBean cobean3 = new CodificacionBean("18686-6", "LN");
        datobean.getCodigos().add(cobean3);
        datobean.setDato("49");
        datobean.setTitulo("frecuencia respiratoria");
        cda.getDatos().add(datobean);



        return cda;

    }

    public static Message getORU(int id) throws Exception {

        String msg = FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/ORU_R01.xml"));

        GenericParser parser = new GenericParser();
        parser.setXMLParserAsPrimary();

        ORU_R01 oru = (ORU_R01) parser.parse(msg);
        oru.getMSH().getMsh10_MessageControlID().setValue(id+"");
        //String pepe = oru.getPATIENT_RESULT().getORDER_OBSERVATIONAll().get(0).getOBSERVATIONAll().get(0).getOBX().getObx5_ObservationValue(0).getData().toString();
        //oru.getMSH().getMsh10_MessageControlID().getValue()
        //System.out.println("CX3: " + pepe);
        
      //  throw new Exception("Peton de copon");
        
        return oru;


    }

    public static Message getHemocultivo(boolean b) throws Exception {
        String msg = null;
        if(b)
            msg = FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/Hemocultivo.xml"));
        else
            msg = FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/hemocultivoNegativo.xml"));
        GenericParser parser = new GenericParser();
        parser.setXMLParserAsPrimary();

        ORU_R01 oru = (ORU_R01) parser.parse(msg);

        List<ORU_R01_ORDER_OBSERVATION> order = oru.getPATIENT_RESULT().getORDER_OBSERVATIONAll();

        for (ORU_R01_ORDER_OBSERVATION o : order) {
            List<ORU_R01_OBSERVATION> observationAll = o.getOBSERVATIONAll();

            for (ORU_R01_OBSERVATION ob : observationAll) {

                String value = ob.getOBX().getObx3_ObservationIdentifier().getCe1_Identifier().getValue();

            }

        }

        return oru;


    }

    public static SepsisSyndrome getSepsisSyndrome() throws Exception {

        SepsisSyndrome sepsisSyndrome = new SepsisSyndrome();
        sepsisSyndrome.setNhc("171194"); // hemocultivo
        // sepsisSyndrome.setNhc("63560");     // oru normal
        sepsisSyndrome.setUUID(UUID.randomUUID().toString());


        return sepsisSyndrome;


    }

    public static SimpleSepsis getSepsisSimple() throws Exception {

        SimpleSepsis simpleSepsis = new SimpleSepsis();
        //simpleSepsis.setNhc("171194"); // hemocultivo
        simpleSepsis.setNhc("63560");     // oru normal
        simpleSepsis.setUUID(UUID.randomUUID().toString());


        return simpleSepsis;


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
