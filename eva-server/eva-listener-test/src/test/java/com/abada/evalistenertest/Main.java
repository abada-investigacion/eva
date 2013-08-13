 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

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

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.group.OMP_O09_ORDER;
import ca.uhn.hl7v2.model.v25.message.*;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import com.abada.eva.test.property.Property;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.beans.CodificacionBean;
import es.sacyl.eva.beans.DatoBean;
import java.io.File;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author mmartin
 */
public class Main {

    private static final Log logger = LogFactory.getLog(Main.class);
    
    /** ip jorge*/
    //private static final String urlhl7 = "http://192.168.1.21:8080/eva-rest/rs/sendmessage";
    
    /** ip david*/     
    //private static final String urlhl7 = "http://192.168.1.22:8080/eva-rest/rs/sendmessage";
    
    /** ip jesus*/ 
    private static final String urlhl7 = "http://192.168.1.35:8080/eva-rest/rs/sendmessage";
    
    /** URLCDA DEBE TENER LA MISMA IP QUE URLHL7**/
    
    private static final String urlcda = "http://192.168.1.35:8080/eva-rest/rs/send";
    
    private static final String path="/home/mmartin/NetBeansProjects";
    
    private static DefaultHttpClient httpclient;
    private static HttpResponse httpResponse = null;
    private static HttpEntity httpEntity = null;
    private static List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    private static Gson json = new GsonBuilder().create();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {


        Property p = new Property();
        Properties pro = p.getProperty();

        /**
         * Envio de CDA
         */
        CDABean cda = getCda();
        sendCda(json.toJson(cda));
////
////        String directory = pro.getProperty("directorio");
////        String[] secuencia = pro.getProperty("secuencia").split(",");
////        Map<String, File> fileMap = getFileMap(directory);
////
////        /**
////         * Envio de mensajes HL7
////         */
////        for (String s : secuencia) {
////            File get = fileMap.get(s);
////            String content = FileUtils.readFileToString(get);
////            //sendHl7(content);
////        }
////
////        /**
////         * Envio de Hemocultivo
////         */
////        sendHl7(getHemocultivo()); 

        if (httpclient != null) {
            httpclient.getConnectionManager().shutdown();
        }


    }

    private static Map<String, File> getFileMap(String directory) throws Exception {


        File directorio = null;

        Map<String, File> fileMap = new HashMap<String, File>();

        try {
            directorio = new File(directory);
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File file : archivos) {
                    fileMap.put(file.getName(), file);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
        return fileMap;
    }

    private static void sendMessage(HttpPost httpPost) throws Exception {
        httpclient = new DefaultHttpClient();

        try {

            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(AuthScope.ANY),
                    new UsernamePasswordCredentials("admin", "admin"));
            httpResponse = httpclient.execute(httpPost);
            httpEntity = httpResponse.getEntity();

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                logger.trace(httpResponse.getStatusLine().toString());


            } else {
                throw new Exception(httpResponse.getStatusLine().getStatusCode() + " Http code response.");
            }
        } catch (Exception e) {
            if (httpEntity != null) {
                logger.error(e);
            } else {
                logger.error(e);
            }
            throw e;
        } finally {
            if (httpEntity != null) {
                httpEntity.getContent().close();
            }
        }
    }

    private static void sendHl7(String message) throws Exception {
        nvps.add(new BasicNameValuePair("hl7", message));
        HttpPost httpPost = new HttpPost(urlhl7);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        sendMessage(httpPost);
        Thread.sleep(5000);
        nvps.clear();
    }

    private static void sendCda(String message) throws Exception {
        nvps.add(new BasicNameValuePair("object", message));
        nvps.add(new BasicNameValuePair("type", CDABean.class.getName()));
        HttpPost httpPost = new HttpPost(urlcda);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        sendMessage(httpPost);
        Thread.sleep(5000);
        nvps.clear();
    }

    public static CDABean getCda() throws Exception {
        CDABean cda = new CDABean("1", "cagarro");
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

    public static String getHemocultivo() throws Exception {

        String msg = FileUtils.readFileToString(new File(path+"/eva/trunk/eva-server/eva-listener-test/src/main/resources/com/abada/eva/test/hl7/messages/Hemocultivo.xml"));

//        GenericParser parser = new GenericParser();
//        parser.setXMLParserAsPrimary();
//
//        ORU_R01 oru = (ORU_R01) parser.parse(msg);


        return msg;


    }

    public static ADT_A01 getMessageADT_A01() throws Exception {

        ADT_A01 adt = new ADT_A01();

        // Populate the MSH Segment          
        MSH mshSegment = adt.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        mshSegment.getMessageType().getMessageCode().setValue("ADT");
        mshSegment.getMessageType().getTriggerEvent().setValue("A01");
        mshSegment.getMessageType().getMessageStructure().setValue("ADT_A01");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");
        // Populate the PID Segment
        PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");

        CX patientIdentifier = pid.getPatientIdentifierList(0);
        patientIdentifier.getCx1_IDNumber().setValue(Integer.toString(1));
        patientIdentifier.getCx5_IdentifierTypeCode().setValue("PI");



        adt.getPID().getAlternatePatientIDPID(0).getCx5_IdentifierTypeCode().getValue();
        adt.getMSH().getMsh9_MessageType().getMsg2_TriggerEvent().getValue();

//         CX[] cxarray = pid.getPid3_PatientIdentifierList();
//        if (cxarray != null && cxarray.length > 0) {
//            for (CX cxx : cxarray) {
//                addPatientId(patient, cxx.getCx1_IDNumber().getValue(), cxx.getCx5_IdentifierTypeCode().getValue());
//            }
//        }

        //pid.getPatientIdentifierList(0).get().setValue("123456")

        return adt;
    }

    public static OMP_O09 getOMP_009() throws Exception {

        OMP_O09 message = new OMP_O09();

        // Populate the MSH Segment          
        MSH mshSegment = message.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("13343");
        mshSegment.getMessageType().getMessageCode().setValue("OMP");
        mshSegment.getMessageType().getTriggerEvent().setValue("O09");
        mshSegment.getMessageType().getMessageStructure().setValue("OMP_O09");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");
        // Populate the PID Segment
        PID pid = message.getPATIENT().getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");

        CX patientIdentifier = pid.insertPid3_PatientIdentifierList(0);
        patientIdentifier.getCx1_IDNumber().setValue(Integer.toString(1));
        patientIdentifier.getCx5_IdentifierTypeCode().setValue("PI");
        OMP_O09_ORDER orderSegment = message.getORDER();
        orderSegment.getORC().getOrc2_PlacerOrderNumber().getEi1_EntityIdentifier().setValue("2");
        message.getPATIENT().getPID().getPatientIdentifierList(0).getCx1_IDNumber().getValue();
        return message;
    }

    public static ADT_A03 getMessageADT_A03() throws Exception {

        ADT_A03 adt = new ADT_A03();

        // Populate the MSH Segment          
        MSH mshSegment = adt.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        mshSegment.getMessageType().getMessageCode().setValue("ADT");
        mshSegment.getMessageType().getTriggerEvent().setValue("A03");
        mshSegment.getMessageType().getMessageStructure().setValue("ADT_A03");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");
        // Populate the PID Segment
        PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");

        CX patientIdentifier = pid.insertPid3_PatientIdentifierList(0);
        patientIdentifier.getCx1_IDNumber().setValue(Integer.toString(1));
        patientIdentifier.getCx5_IdentifierTypeCode().setValue("PI");


        //

//         CX[] cxarray = pid.getPid3_PatientIdentifierList();
//        if (cxarray != null && cxarray.length > 0) {
//            for (CX cxx : cxarray) {
//                addPatientId(patient, cxx.getCx1_IDNumber().getValue(), cxx.getCx5_IdentifierTypeCode().getValue());
//            }
//        }

        //pid.getPatientIdentifierList(0).get().setValue("123456")

        return adt;
    }

    public static String getMessageADT_A31() throws Exception {

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


        return p.encode(adt);
    }

    public static String getMessageORU_R01() throws Exception {

        ORU_R01 oru = new ORU_R01();

        // Populate the MSH Segment          
        MSH mshSegment = oru.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        mshSegment.getMessageType().getMessageCode().setValue("ORU");
        mshSegment.getMessageType().getTriggerEvent().setValue("R01");
        mshSegment.getMessageType().getMessageStructure().setValue("ORU_R01");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");
        // Populate the PID Segment
        PID pid = (PID) oru.get("PID");
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");


        pid.getPatientIdentifierList(0).getCx1_IDNumber().setValue(Integer.toString(1));
        pid.getPatientIdentifierList(0).getCx5_IdentifierTypeCode().setValue("PI");

        PV1 pv1 = (PV1) oru.get("PV1");
        pv1.getReferringDoctor(0).getGivenName().setValue("Dr. who");





        Parser p = new DefaultXMLParser();


        return p.encode(oru);
    }

    public static String getMessageOMD_O03() throws Exception {

        OMD_O03 omd_o03 = new OMD_O03();

        PID pid = omd_o03.getPATIENT().getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
        pid.getPatientName(0).getGivenName().setValue("John");
        pid.getPatientIdentifierList(0).getCx1_IDNumber().setValue(Integer.toString(1));
        pid.getPatientIdentifierList(0).getCx5_IdentifierTypeCode().setValue("PI");

        // Populate the MSH Segment          
        MSH mshSegment = omd_o03.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTime().setValue("200701011539");
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
        mshSegment.getSequenceNumber().setValue("123");
        mshSegment.getMessageType().getMessageCode().setValue("OMD");
        mshSegment.getMessageType().getTriggerEvent().setValue("O03");
        mshSegment.getMessageType().getMessageStructure().setValue("OMD_O03");
        mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.5");


        ODS ods = omd_o03.getORDER_DIET().getDIET().getODS();
        ods.getOds1_Type().setValue("S");
        ods.getOds2_ServicePeriod(0).getCe2_Text().setValue("breakfast");
        ods.getOds3_DietSupplementOrPreferenceCode(0).getCe2_Text().setValue("320^1/2 HAM SANDWICH^99FD8");
        System.out.println(ods.getOds3_DietSupplementOrPreferenceCode(0).getCe2_Text().getValue());

        //

//         CX[] cxarray = pid.getPid3_PatientIdentifierList();
//        if (cxarray != null && cxarray.length > 0) {
//            for (CX cxx : cxarray) {
//                addPatientId(patient, cxx.getCx1_IDNumber().getValue(), cxx.getCx5_IdentifierTypeCode().getValue());
//            }
//        }

        //pid.getPatientIdentifierList(0).get().setValue("123456")

        Parser p = new DefaultXMLParser();

        String aux = p.encode(omd_o03);

        Message m = p.parse(aux);

        String aux2 = p.encode(m);

        return aux2;
        // return p.encode(omd_o03);
    }
}
