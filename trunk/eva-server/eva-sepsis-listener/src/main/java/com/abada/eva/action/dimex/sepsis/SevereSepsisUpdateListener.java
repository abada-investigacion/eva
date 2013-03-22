/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.ORU_R01_ORDER_OBSERVATION;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.model.v25.segment.OBX;
import com.abada.eva.action.dimex.AbstractDimexUpdateListener;
import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.beans.CodificacionBean;
import es.sacyl.eva.beans.DatoBean;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class SevereSepsisUpdateListener extends AbstractDimexUpdateListener<SevereSepsis>{
    
    private String url;
    private Map<String, String> symptoms;
    

    public SevereSepsisUpdateListener() {
        super();
    }

    @Override
    protected Map<String, Object> getData(Object[] oldMessages, Object[] newMessages) {

        Map<String, Object> values = new HashMap<String, Object>();

        for (Object event : newMessages) {
            if (event instanceof ORU_R01) {
                try {
                    this.addOruValues(values, (ORU_R01) event);
                } catch (HL7Exception ex) {
                    
                }
            }else if (event instanceof SimpleSepsis) {
                values.put(SepsisConstants.NHC, ((SimpleSepsis)event).getNhc());
            } else if (event instanceof CDABean) {
                this.addCDAValues(values, (CDABean) event);
            }
        }
        
        values.put(SepsisConstants.SIMPLE_SEPSIS, true);
        
        return values;
    }

    @Override
    protected URI getUrl(Map<String, Object> data) {
        try {
            return new URI(this.url);
        } catch (URISyntaxException ex) {
        }
        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void addOruValues(Map<String, Object> values, ORU_R01 oru) throws HL7Exception {

        for (ORU_R01_ORDER_OBSERVATION order : oru.getPATIENT_RESULT().getORDER_OBSERVATIONAll()) {
            for (ORU_R01_OBSERVATION obs : order.getOBSERVATIONAll()) {
                OBX obx = obs.getOBX();
                String code = obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue();
                String system = obx.getObx3_ObservationIdentifier().getCe3_NameOfCodingSystem().getValue();
                String value = obx.getObx5_ObservationValue(0).getData().toString();
                if(symptoms.containsKey(code+system)){
                    values.put(symptoms.get(code+system),value);
                }
            }
        }

    }

    private void addCDAValues(Map<String, Object> values, CDABean cdaBean) {
        for (DatoBean dat : cdaBean.getDatos()) {
            for(CodificacionBean cb : dat.getCodigos()){
                if(symptoms.containsKey(cb.getCode()+cb.getCodeSystem())){
                    values.put(symptoms.get(cb.getCode()+cb.getCodeSystem()), dat.getDato());
                }
            }
        }
       
    }
    
    public Map<String, String> getSymptoms() {
        return symptoms;
    }
    
    public void setSymptoms(Map<String, String> symptoms) {
        this.symptoms = symptoms;
    }
}