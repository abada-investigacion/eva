/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.selene.v25.message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.ELD;
import ca.uhn.hl7v2.model.v25.segment.ERR;
import ca.uhn.hl7v2.model.v25.segment.MSA;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.util.EncodedMessageComparator;
import com.abada.generator.object.Order;
import com.abada.generator.object.Patient;
import com.abada.generator.object.checks.ArticuloChecks;
import com.abada.generator.object.checks.DuracionChecks;
import com.abada.generator.object.checks.PrincipioActivoChecks;
import com.abada.generator.enums.Messages;
import com.abada.generator.object.checks.Adt17Checks;
import com.abada.generator.object.checks.AdtCamaChecks;
import com.abada.generator.object.Order;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author david
 *
 * Generamos mensaje omp_009 y ADT
 */
public class SeleneMessagesMaker {

    private static Log log = LogFactory.getLog(SeleneMessagesMaker.class);

    /**
     * creado de mensaje Omp_009 y validación y lanzados al webservice
     * @param Order
     * @return
     * @throws HL7Exception
     * @throws Exception
     */
    public String createListOMP_O09(Order Order) throws HL7Exception, Exception {
String encodedXmlMessage="";
        SeleneOMP09Creator OMP09Creator = new SeleneOMP09Creator();
        Parser parser = new DefaultXMLParser();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        boolean validate = true;

        if (!validateOrder(validator, Order)) {
            validate = false;
        }
        if (Order.getModo_prescripcion() != null) {
            if (Order.getModo_prescripcion().equalsIgnoreCase("A")) {
                if (!validateOrder(validator, Order, ArticuloChecks.class)) {
                    validate = false;
                }
            } else if (Order.getModo_prescripcion().equalsIgnoreCase("P")) {
                if (!validateOrder(validator, Order, PrincipioActivoChecks.class)) {
                    validate = false;
                }
            }
        }
        if (Order.getFecha_fin() != null && Order.getHora_duracion() != null) {
            if (!validateOrder(validator, Order, DuracionChecks.class)) {
                validate = false;
            }
        }
        //If measure unit is assumed => warning the user
        if (Order.getId_measure_unit() != null) {
            if (Order.getMeasure_unit_en_mambrino() != null && Order.getMeasure_unit_en_mambrino().equalsIgnoreCase("N")) {
                log.warn("Asuming " + Order.getId_measure_unit() + " for id_measure_unit in message OMP_O09 with orderid: " + Order.getOrderid());
            }
        }
        if (validate) {
            encodedXmlMessage = parser.encode(OMP09Creator.exec(Order));
            log.info("Generated OK message OMP_O09 orderid: " + Order.getOrderid() + " Control: " + Order.getControl());
            //conexion con client webservice
        

        }
        return encodedXmlMessage;
    }

    /**
     * validado por defecto de prescripcion
     * @param validator
     * @param Order
     * @return
     */
    private boolean validateOrder(Validator validator, Order Order) {
        return validateOrder(validator, Order, Default.class);
    }

    /**
     * Validado de una prescripción
     * @param validator
     * @param Order
     * @param group
     * @return
     */
    private boolean validateOrder(Validator validator, Order Order, Class<?> group) {
        boolean validate = true;
        Set<ConstraintViolation<Order>> constraintViolations = validator.validate(Order, group);
        if (constraintViolations.size() > 0) {
            validate = false;
            Iterator<ConstraintViolation<Order>> iterator = constraintViolations.iterator();
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("\nError KO Order: ").append(Order.getOrderid()).append(" numerohc_nproceso_linea");

            while (iterator.hasNext()) {
                ConstraintViolation<Order> consViolation = iterator.next();
                errorMessage.append("\nError on ").append(consViolation.getRootBeanClass().getCanonicalName()).append(" ").append(consViolation.getPropertyPath()).append(" ").append(consViolation.getMessage());
                errorMessage.append("\n");
            }
            errorMessage.append("\n");
            log.error(errorMessage);
        }
        return validate;
    }

    /**
     * validado por defecto un paciente
     * @param validator
     * @param Patient
     * @return
     */
    private boolean validatePatient(Validator validator, Patient Patient) {
        return validatePatient(validator, Patient, Default.class);
    }

    /**
     * Validado de un paciente
     * @param validator
     * @param Patient
     * @param group
     * @return
     */
    private boolean validatePatient(Validator validator, Patient Patient, Class<?> group) {
        boolean validate = true;
        Set<ConstraintViolation<Patient>> constraintViolations = validator.validate(Patient, group);
        if (constraintViolations.size() > 0) {
            validate = false;
            Iterator<ConstraintViolation<Patient>> iterator = constraintViolations.iterator();
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("\nError KO Patient; numerohc : ").append(Patient.getNumerohc());

            while (iterator.hasNext()) {
                ConstraintViolation<Patient> consViolation = iterator.next();
                errorMessage.append("\nError on ").append(consViolation.getRootBeanClass().getCanonicalName()).append(" ").append(consViolation.getPropertyPath()).append(" ").append(consViolation.getMessage());
                errorMessage.append("\n");
            }
            errorMessage.append("\n");
            log.error(errorMessage);
        }
        return validate;
    }

    /**
     * creación de los distintos mensajes Adt segun venga y lanzados al webservice
     * @param patient
     * @return
     * @throws HL7Exception
     * @throws Exception
     */
    public String createListADT(Patient patient) throws HL7Exception, Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        SeleneStructureADT_A01Creator seleneStructureADT_A01Creator = new SeleneStructureADT_A01Creator();
        SeleneStructureADT_A03Creator seleneStructureADT_A03Creator = new SeleneStructureADT_A03Creator();
        SeleneStructureADT_A05Creator seleneStructureADT_A05Creator = new SeleneStructureADT_A05Creator();
        SeleneStructureADT_A17Creator seleneStructureADT_A17Creator = new SeleneStructureADT_A17Creator();
        SeleneStructureADT_A21Creator seleneStructureADT_A21Creator = new SeleneStructureADT_A21Creator();
        SeleneStructureADT_A30Creator seleneStructureADT_A30Creator = new SeleneStructureADT_A30Creator();
        Parser parser = new DefaultXMLParser();
        String encodedXmlMessage = "";
        boolean valid = false;
        if (validatePatient(validator, patient)) {
            if (patient.getTriggerEvent().equals(Messages.A01.toString()) && validatePatient(validator, patient, AdtCamaChecks.class)) {
                log.info("Generated OK message ADT_A01 (ADMISSION) Patient numhc: " + patient.getNumerohc() + " bed: " + patient.getPaciente_ncama());
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A01Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A02.toString()) && validatePatient(validator, patient, AdtCamaChecks.class)) {
                if (patient.getPatientswapcama() == null) {
                    log.info("Generated OK message ADT_A02 (TRANSFER BED) Patient numhc: " + patient.getNumerohc() + " bed: " + patient.getPaciente_ncama());
                } else {
                    log.info("Generated OK message ADT_A02 (TRANSFER BED) Patient numhc: " + patient.getNumerohc() + " old bed: " + patient.getPatientswapcama() + "-> bed: " + patient.getPaciente_ncama());
                }
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A01Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A03.toString())) {
                log.info("Generated OK message ADT_A03 (ALTA) Patient numhc: " + patient.getNumerohc() + " free bed: " + patient.getPatientswapcama());
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A03Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A17.toString())
                    && validatePatient(validator, patient, Adt17Checks.class) && validatePatient(validator, patient, AdtCamaChecks.class)) {
                log.info("Generated OK message ADT_A17 (BED SWAP) Patients numhc: " + patient.getNumerohc() + " bed: " + patient.getPaciente_ncama() + ", numhc: " + patient.getPatientswapnumerohc() + " bed: " + patient.getPatientswapcama());
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A17Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A28.toString())) {
                if (patient.getPaciente_ncama() == null) {
                    log.info("Generated OK message ADT_A28 (NEW PATIENT) Patient numhc: " + patient.getNumerohc());
                } else {
                    log.info("Generated OK message ADT_A28 (NEW PATIENT) Patient numhc: " + patient.getNumerohc() + " bed: " + patient.getPaciente_ncama());
                }
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A05Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A29.toString())) {
                log.info("Generated OK message ADT_A29 (DELETE PATIENT) Patient numhc: " + patient.getNumerohc());
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A21Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A31.toString())) {
                log.info("Generated OK message ADT_A31 (UPDATE PATIENT DEMOGRAFIC) Patient numhc: " + patient.getNumerohc());
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A05Creator.exec(patient));
            } else if (patient.getTriggerEvent().equals(Messages.A47.toString())) {
                if (patient.getNumerohcanterior() == null) {
                    log.info("Generated OK message ADT_A47 (UPDATE ID PATIENT) Patient numhc: " + patient.getNumerohc());
                } else {
                    log.info("Generated OK message ADT_A47 (UPDATE ID PATIENT) Patient numhc: " + patient.getNumerohcanterior() + " new id numhc: " + patient.getNumerohc());
                }
                valid = true;
                encodedXmlMessage = parser.encode(seleneStructureADT_A30Creator.exec(patient));
            } 
            if (valid) {
               return encodedXmlMessage;
            }

        }
        return null;
    }

    /**
     * lanzado de mensajes al webservice capturando excepciones hl7 o exception
     * @param menssage
     * @throws HL7Exception
     * @throws Exception
     */
    private void clientwebService(String menssage, URL urlwebservice, String logOriginalOnInvalid) throws HL7Exception, Exception {
    }
}
