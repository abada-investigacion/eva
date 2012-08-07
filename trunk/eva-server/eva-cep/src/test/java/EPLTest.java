

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author mmartin
 */
public class EPLTest {
/*

    public static void main(String[] args) throws Exception {

        URL esper = new URL("file:/home/mmartin/NetBeansProjects/eva/trunk/eva-server/eva-rest/src/main/resources/META-INF/hl7.esper.config.cfg.xml");
        URL sta = new URL("file:/home/mmartin/NetBeansProjects/eva/trunk/eva-server/eva-rest/src/main/resources/META-INF/statement.esper.config.cfg.xml");

        EsperLoader el = new EsperLoader(esper);

        EsperService es = new EsperService(sta, el);

        es.send(new CustomEPL(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
        Thread.currentThread().sleep(5000);
        es.send(new CustomEPL(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
        Thread.currentThread().sleep(5000);
        es.send(new CustomEPL(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
        Thread.currentThread().sleep(5000);
        es.send(new CustomEPL(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
        Thread.currentThread().sleep(15000);
        es.send(new CustomEPL(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());

    }

    public static ADT_A01 getMessage() throws Exception {

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
        //pid.getPatientIdentifierList(0).get().setValue("123456")

        return adt;
    }

    public static ACK getACK() throws Exception {

        return (ACK) getMessage().generateACK();
    }
    */
}
