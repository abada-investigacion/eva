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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class SimpleSepsisUpdateListener extends AbstractDimexUpdateListener<SimpleSepsis> {

    private String url;

    public SimpleSepsisUpdateListener() {
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
            } else if (event instanceof SepsisSyndrome) {
                values.put(SepsisConstants.NHC, ((SepsisSyndrome) event).getNhc());
            }
        }
        values.put(SepsisConstants.SEPSIS_SYMDROME, true);

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

                if (obx.getObx3_ObservationIdentifier().getCe1_Identifier().getValue().equals(SepsisConstants.MICROBIOLOGY_CODE)) {
                    values.put(SepsisConstants.MICROBIOLOGY, true);
                }

            }
        }
        if (!values.containsKey(SepsisConstants.MICROBIOLOGY)) {
            values.put(SepsisConstants.MICROBIOLOGY, false);
        }
    }
}
