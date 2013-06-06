/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.model.v25.segment.OBX;
import com.abada.eva.api.EsperService;
import com.abada.eva.api.SetEsperService;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class CustomORUUpdateListener implements UpdateListener, SetEsperService {

    private EsperService cep;
    private Map<String, String> symptoms;

    public EsperService getCep() {
        return cep;
    }

    public void setCep(EsperService cep) {
        this.cep = cep;
    }

    public Map<String, String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Map<String, String> symptoms) {
        this.symptoms = symptoms;
    }
    
    public void update(EventBean[] ebs, EventBean[] ebs1) {
        if (ebs != null) {
            for (EventBean e : ebs) {

                ORU_R01 oru = (ORU_R01) e.getUnderlying();
                ORU_R01Custom oruc = getCustomORU(oru);
                if (oruc.isHemocultivo()) {
                    addHemocultiveValue(oru, oruc);
                }else{
                    addOruValues(oru, oruc);
                }
                
                cep.send(oruc);
            }
        }
    }

    public void setEsperService(EsperService esperService) {
        cep = esperService;
    }

    public EsperService getEsperService() {
        return cep;
    }

    private ORU_R01Custom getCustomORU(ORU_R01 oru) {
        ORU_R01Custom oruc = new ORU_R01Custom();

        oruc.setMessageId(oru.getMSH().getMsh10_MessageControlID().getValue());
        oruc.setNhc(getNHC(oru));
        oruc.setHemocultivo(isHemocultive(oru));


        return oruc;
    }

    private String getNHC(ORU_R01 oru) {

        CX[] pid3 = oru.getPATIENT_RESULT().getPATIENT().getPID().getPatientIdentifierList();

        for (int i = 0; i < pid3.length; i++) {

            String code = pid3[i].getCx5_IdentifierTypeCode().getValue();
            String id = pid3[i].getCx1_IDNumber().getValue();

            if (code.equals("PI")) {
                return id;
            }
        }

        return null;
    }

    private boolean isHemocultive(ORU_R01 oru) {
        try {
            for (int i = 0; i < oru.getPATIENT_RESULT().getORDER_OBSERVATIONReps(); i++) {

                ORU_R01_ORDER_OBSERVATION obs = oru.getPATIENT_RESULT().getORDER_OBSERVATION(i);

                for (int e = 0; e < obs.getOBSERVATIONReps(); e++) {

                    OBX obx = obs.getOBSERVATION(e).getOBX();

                    String code = obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue();

                    String codeSystem = obx.getObx3_ObservationIdentifier().getCe3_NameOfCodingSystem().getValue();


                    if ((code.equals("6463-4") || code.equals("41852-5")) && codeSystem.equals("LN")) {
                        return true;
                    }

                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private void addOruValues(ORU_R01 oru, ORU_R01Custom oruc) {
        HashMap<String,Object> values = new HashMap<String, Object>();
        for (int i=0;i<oru.getPATIENT_RESULT().getORDER_OBSERVATIONReps();i++) {
            ORU_R01_ORDER_OBSERVATION order = oru.getPATIENT_RESULT().getORDER_OBSERVATION(i);
            
            for (int i2 = 0; i2 < order.getOBSERVATIONReps(); i2++) {            
                OBX obx = order.getOBSERVATION(i2).getOBX();
                String code = obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue();
                String system = obx.getObx3_ObservationIdentifier().getCe3_NameOfCodingSystem().getValue();
                String value = obx.getObx5_ObservationValue(0).getData().toString();
                if(symptoms.containsKey(code+system)){
                    values.put(symptoms.get(code+system),value);
                }
            }
        }
        
        oruc.setSymptons(values);
    }

    private void addHemocultiveValue(ORU_R01 oru, ORU_R01Custom oruc) {
        HashMap<String,Object> values = new HashMap<String, Object>();
        for (int i=0;i<oru.getPATIENT_RESULT().getORDER_OBSERVATIONReps();i++) {
            ORU_R01_ORDER_OBSERVATION order = oru.getPATIENT_RESULT().getORDER_OBSERVATION(i);
            for (int i2 = 0; i2 < order.getOBSERVATIONReps(); i2++) {            
                OBX obx = order.getOBSERVATION(i2).getOBX();

                if (obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue().equals(SepsisConstants.MICROBIOLOGY_CODE)) {
                    values.put(SepsisConstants.MICROBIOLOGY, true);
                }

            }
        }
        if (!values.containsKey(SepsisConstants.MICROBIOLOGY)) {
            values.put(SepsisConstants.MICROBIOLOGY, false);
        }
        oruc.setSymptons(values);
    }
}
