/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.group.OMP_O09_ORDER;
import ca.uhn.hl7v2.model.v25.message.*;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import com.abada.eva.test.property.Property;
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
    private static final String url = "http://localhost:8080/eva-rest/rs/sendmessage";
    private static DefaultHttpClient httpclient;
    private static HttpResponse httpResponse = null;
    private static HttpEntity httpEntity = null;
    private static List<NameValuePair> nvps = new ArrayList<NameValuePair>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
      
        System.out.println(Main.getMessageADT_A01());
       
        Property p = new Property();
        Properties pro = p.getProperty();

        String directory = pro.getProperty("directorio");
        String[] secuencia = pro.getProperty("secuencia").split(",");
        Map<String, File> fileMap = getFileMap(directory);

        for (String s : secuencia) {
            File get = fileMap.get(s);
            String content = FileUtils.readFileToString(get);
            send(content);
        }


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

    private static void send(String message) throws Exception {
        nvps.add(new BasicNameValuePair("hl7", message));
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        sendMessage(httpPost);
        Thread.sleep(5000);
        nvps.clear();
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

        PV1 pv1 = (PV1)oru.get("PV1");
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
        System.out.println(  ods.getOds3_DietSupplementOrPreferenceCode(0).getCe2_Text().getValue());

        //

//         CX[] cxarray = pid.getPid3_PatientIdentifierList();
//        if (cxarray != null && cxarray.length > 0) {
//            for (CX cxx : cxarray) {
//                addPatientId(patient, cxx.getCx1_IDNumber().getValue(), cxx.getCx5_IdentifierTypeCode().getValue());
//            }
//        }

        //pid.getPatientIdentifierList(0).get().setValue("123456")
        
        Parser p = new DefaultXMLParser();
        
        String aux=p.encode(omd_o03);
        
        Message m=p.parse(aux);

        String aux2=p.encode(m);

        return aux2;
       // return p.encode(omd_o03);
    }
}
